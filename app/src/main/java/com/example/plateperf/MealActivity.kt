package com.example.plateperf

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealActivity : AppCompatActivity() {
    private lateinit var recyclerViewBreakfast: RecyclerView
    private lateinit var recyclerViewLunch: RecyclerView
    private lateinit var recyclerViewDinner: RecyclerView
    private lateinit var backButton: Button
    private lateinit var loaderAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_page)

        recyclerViewBreakfast = findViewById(R.id.recyclerViewBreakfast)
        recyclerViewLunch = findViewById(R.id.recyclerViewLunch)
        recyclerViewDinner = findViewById(R.id.recyclerViewDinner)
        backButton = findViewById(R.id.backBtn)
        loaderAnimationView = findViewById(R.id.loaderAnimationView)

        backButton.setOnClickListener {
            onBackPressed()
        }

        loaderAnimationView.visibility = View.VISIBLE
        loaderAnimationView.playAnimation()

        val apiKey = "1b2c5dfeedce452ea254ea6b66ba41dd"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RecipeService::class.java)

        // Make API call for each meal type
        service.getRecipes("breakfast").enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                handleResponse(response, recyclerViewBreakfast)
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle network errors
                Log.e("MealActivity", "Error fetching breakfast recipes", t)
            }
        })

        service.getRecipes("lunch").enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                handleResponse(response, recyclerViewLunch)
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle network errors
                Log.e("MealActivity", "Error fetching lunch recipes", t)
            }
        })

        service.getRecipes("dinner").enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                handleResponse(response, recyclerViewDinner)
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle network errors
                Log.e("MealActivity", "Error fetching dinner recipes", t)
            }
        })
    }

    private fun handleResponse(response: Response<List<Recipe>>, recyclerView: RecyclerView) {
        loaderAnimationView.visibility = View.GONE
        if (response.isSuccessful) {
            val recipes = response.body()
            if (recipes != null) {
                val adapter = RecipeAdapter(ArrayList(recipes))
                recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = adapter
            } else {
                Log.d("MealActivity", "No recipes received")
            }
        } else {
            Log.d("MealActivity", "Failed to fetch recipes: ${response.code()}")
        }
    }
}
