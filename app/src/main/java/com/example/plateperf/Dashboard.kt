package com.example.plateperf

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.plateperf.databinding.FragmentDashboardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Dashboard.newInstance] factory method to
 * create an instance of this fragment.
 */
class Dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        // Access the Card with the ID "mealTimeNotificationCardView"
        val mealRecipeCardView = binding.mealRecipies

        val notifyCardView = binding.mealTimeNotificationCardView

        // Set an OnClickListener for the Card
        mealRecipeCardView.setOnClickListener {
            Toast.makeText(requireContext(), "Meal Recipie card clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), MealActivity::class.java)
            startActivity(intent)
        }

        notifyCardView.setOnClickListener {
            Toast.makeText(requireContext(), "Notify card clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the binding when the fragment is destroyed
        _binding = null
    }
}