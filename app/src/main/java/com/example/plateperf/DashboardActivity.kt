package com.example.plateperf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.plateperf.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Dashboard())
        firebaseAuth = FirebaseAuth.getInstance()

        // Sign out button click listener

        binding.dashboardSignOutButton.setOnClickListener {
            Log.d("dashboardActivity", "Sign out button clicked") // Log message
            Toast.makeText(this, getString(R.string.sign_out_button_clicked), Toast.LENGTH_SHORT)
                .show() // Toast message
            firebaseAuth.signOut()
            // Redirect to the sign-in activity after signing out
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish() // Close the Activity
        }

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(Dashboard())
                R.id.profile -> replaceFragment(Profile())
                R.id.contactus -> replaceFragment(ContactUs())

                else ->{

                }

            }
            true

        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }
}