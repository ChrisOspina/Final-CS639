package com.example.plateperf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.plateperf.databinding.ActivityMainBinding
import com.example.plateperf.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Sign out button click listener
        binding.signOutButton.setOnClickListener {
            Log.d("MainActivity", "Sign out button clicked") // Log message
            Toast.makeText(this, "Sign out button clicked", Toast.LENGTH_SHORT).show() // Toast message
            firebaseAuth.signOut()
            // Redirect to the sign-in activity after signing out
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // Close the MainActivity
        }
    }
}