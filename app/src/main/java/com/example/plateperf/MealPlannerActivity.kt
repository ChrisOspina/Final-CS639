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


class MealPlannerActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var mealPlannerAdapter: MealPlannerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_planner)

        backButton = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.mealsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mealPlannerAdapter = MealPlannerAdapter(mutableListOf())
        recyclerView.adapter = mealPlannerAdapter

        backButton.setOnClickListener {
            onBackPressed()
        }

        // Call function to fetch meal plan data
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
        val apiKey = "1b2c5dfeedce452ea254ea6b66ba41dd"
        val call = service.getMealPlan(apiKey)

        call.enqueue(object : Callback<MealPlanResponse> {
            override fun onResponse(call: Call<MealPlanResponse>, response: Response<MealPlanResponse>) {
                if (response.isSuccessful) {
                    val mealPlanResponse = response.body()
                    mealPlanResponse?.let {
                        val days = listOf(
                            it.week.monday,
                            it.week.tuesday,
                            it.week.wednesday,
                            it.week.thursday,
                            it.week.friday,
                            it.week.saturday,
                            it.week.sunday
                        )
                        mealPlannerAdapter.updateDays(days)
                    } ?: Log.e("MealPlannerActivity", "Meal plan response is null")
                } else {
                    Log.e("MealPlannerActivity", "Failed to fetch meal plan: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MealPlanResponse>, t: Throwable) {
                Log.e("MealPlannerActivity", "Error fetching meal plan", t)
            }
        })
    }
}
