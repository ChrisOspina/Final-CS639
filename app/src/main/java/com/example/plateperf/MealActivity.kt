package com.example.plateperf

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.plateperf.interfaces.AppService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import layout.MealAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MealActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button
    private lateinit var loaderAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        recyclerView = findViewById(R.id.recyclerView)
        backButton = findViewById(R.id.backBtn)
        loaderAnimationView = findViewById(R.id.loaderAnimationView)


        backButton.setOnClickListener {
            onBackPressed()
        }

        loaderAnimationView.visibility = View.VISIBLE
        loaderAnimationView.playAnimation()

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create service instance
        val service = retrofit.create(AppService::class.java)

        // Make API call and enqueue it to execute asynchronously
        val call = service.getRecipes("1b2c5dfeedce452ea254ea6b66ba41dd")


        call.enqueue(object : retrofit2.Callback<recipie> {
            override fun onResponse(call: Call<recipie>, response: retrofit2.Response<recipie>) {
                loaderAnimationView.visibility = View.GONE
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    if (recipeResponse != null) {
                        // Handle successful response
                        val meals = recipeResponse.results
                        Log.d("MealActivity", "Meals: $meals")
                        val adapter = MealAdapter(meals)
                        recyclerView.adapter = adapter
                        Log.d("API Response", meals.toString())
                    } else {
                        // Handle empty response
                        Log.d("API Response", "No recipes received")
                    }
                } else {
                    // Handle API request failure
                    Log.d("API Response", "Failed to fetch recipes: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<recipie>, t: Throwable) {
                // Handle network errors
            }
        })
        }
    }

