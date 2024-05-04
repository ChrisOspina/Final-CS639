package com.example.plateperf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.plateperf.interfaces.AppService
import layout.AllergyAdapter
import layout.MealAdapter
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllergyList : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var loaderAnimationView: LottieAnimationView
    private lateinit var backButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergy_list)

        recyclerView = findViewById(R.id.recyclerView)
        backButton = findViewById(R.id.backBtn)
        loaderAnimationView = findViewById(R.id.loaderAnimationView)

        loaderAnimationView.visibility = View.VISIBLE
        loaderAnimationView.playAnimation()

        backButton.setOnClickListener {
            onBackPressed()
        }

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create service instance
        val service = retrofit.create(AppService::class.java)
        val allergy = intent.getStringExtra("key")
        val allergyFilter = allergy.toString()

        Log.e("allergy filter", allergyFilter)

        // Make API call and enqueue it to execute asynchronously
        val call = service.getAllergy("1883dce1113c4fdca503a77c0dad69c7", allergyFilter)

        call.enqueue(object : retrofit2.Callback<allergy> {
            override fun onResponse(call: Call<allergy>, response: retrofit2.Response<allergy>) {
                loaderAnimationView.visibility = View.GONE
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    if (recipeResponse != null) {
                        // Handle successful response
                        val meals = recipeResponse.results
                        Log.d("Allergy", "Allergy: $meals")
                        val adapter = AllergyAdapter(meals)
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

            override fun onFailure(call: Call<allergy>, t: Throwable) {
                // Handle network errors
            }
        })



    }
}