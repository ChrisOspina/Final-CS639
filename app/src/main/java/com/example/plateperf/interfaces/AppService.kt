package com.example.plateperf.interfaces

import com.example.plateperf.MealPlanResponse
import com.example.plateperf.Resultmeal
import com.example.plateperf.recipie
import com.example.plateperf.allergy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("recipes/complexSearch")
    fun getRecipes(
        @Query("apiKey") apiKey: String,
    ): Call<recipie>


    @GET("mealplanner/generate")
    fun getMealPlan(
        @Query("apiKey") apiKey: String,
    ): Call<MealPlanResponse>

    @GET("recipes/complexSearch")
    fun getAllergy(
        @Query("apiKey") apiKey: String, @Query("intolerances") intolerances:String, @Query("offset") offset: Int = 10
    ): Call<allergy>
}
