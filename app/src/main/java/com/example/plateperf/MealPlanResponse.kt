package com.example.plateperf

data class MealPlanResponse(
    val week: Week
)

data class Week(
    val monday: Day,
    val tuesday: Day,
    val wednesday: Day,
    val thursday: Day,
    val friday: Day,
    val saturday: Day,
    val sunday: Day
)

data class Day(
    val meals: List<Meal>,
    val nutrients: Nutrients
)

data class Meal(
    val id: Int,
    val imageType: String,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String
)

data class Nutrients(
    val calories: Double,
    val protein: Double,
    val fat: Double,
    val carbohydrates: Double
)
