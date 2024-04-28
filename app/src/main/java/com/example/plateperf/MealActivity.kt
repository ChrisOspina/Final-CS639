package com.example.plateperf

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        val backButton: Button = findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
