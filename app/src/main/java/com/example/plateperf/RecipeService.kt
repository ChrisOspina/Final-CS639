package com.example.plateperf

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes")
    fun getRecipes(@Query("type") type: String): Call<List<Recipe>>
}
