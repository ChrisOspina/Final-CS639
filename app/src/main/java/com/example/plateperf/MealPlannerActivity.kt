package com.example.plateperf

import MealPlannerAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plateperf.interfaces.AppService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealPlannerActivity  : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealPlannerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_planner)

        val backButton: Button = findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            onBackPressed()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MealPlannerAdapter(emptyList())
        recyclerView.adapter = adapter

        // Call the function to fetch meal plan data
        fetchMealPlan()

    }

    private fun fetchMealPlan() {
        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create service instance
        val service = retrofit.create(AppService::class.java)

        // Make API call and enqueue it to execute asynchronously
        val call = service.getMealPlan("1b2c5dfeedce452ea254ea6b66ba41dd")

        call.enqueue(object : Callback<MealPlanResponse> {
            override fun onResponse(call: Call<MealPlanResponse>, response: Response<MealPlanResponse>) {
                if (response.isSuccessful) {
                    val mealPlanResponse = response.body()
                    if (mealPlanResponse != null) {
                        // Process the meal plan response here
                        val week = mealPlanResponse.week

                        // Create a list of all meals
                        val allMeals = mutableListOf<Meal>()
                        allMeals.addAll(week.monday.meals)
                        allMeals.addAll(week.tuesday.meals)
                        allMeals.addAll(week.wednesday.meals)
                        allMeals.addAll(week.thursday.meals)
                        allMeals.addAll(week.friday.meals)
                        allMeals.addAll(week.saturday.meals)
                        allMeals.addAll(week.sunday.meals)

                        // Update adapter with the list of meals
                        adapter.updateMeals(allMeals)
                    } else {
                        Log.e("MealPlanner", "Meal plan response is null")
                    }
                } else {
                    // Handle API request failure
                    Log.e("MealPlanner", "Failed to fetch meal plan: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MealPlanResponse>, t: Throwable) {
                // Handle network errors
                Log.e("MealPlanner", "Error fetching meal plan", t)
            }
        })
    }
}