package com.example.plateperf

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.plateperf.interfaces.AppService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealPlannerActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_planner)

        val backButton: Button = findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            onBackPressed()
        }

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
                        // Access Monday meals
                        val mondayMeals = week.monday.meals
                        // Log meals
                        for (meal in mondayMeals) {
                            Log.d("MealPlanner", "Meal Title: ${meal.title}")
                            Log.d("MealPlanner", "Meal Source URL: ${meal.sourceUrl}")
                            // Log other meal properties as needed
                        }
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