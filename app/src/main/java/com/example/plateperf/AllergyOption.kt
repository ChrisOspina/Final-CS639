package com.example.plateperf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllergyOption.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllergyOption : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("fragment", "in fragment")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_allergy_option, container, false)

        // Dairy Filter
        val dairyButton : Button = view.findViewById(R.id.dairyButton)
        dairyButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Dairy")
            startActivity(intent)

        }

        // Egg
        val eggButton : Button = view.findViewById(R.id.eggButton)
        eggButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Egg")
            startActivity(intent)
        }

        // Gluten
        val glutenButton : Button = view.findViewById(R.id.glutenButton)
        glutenButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Gluten")
            startActivity(intent)
        }

        // Peanut
        val peanutButton : Button = view.findViewById(R.id.peanutButton)
        peanutButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Peanut")
            startActivity(intent)
        }

        // Shellfish
        val shellfishButton : Button = view.findViewById(R.id.shellfishButton)
        shellfishButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Shellfish")
            startActivity(intent)
        }

        // Soy
        val soyButton : Button = view.findViewById(R.id.soyButton)
        soyButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Soy")
            startActivity(intent)
        }

        // Grain
        val grainButton : Button = view.findViewById(R.id.grainButton)
        grainButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Grain")
            startActivity(intent)
        }

        // Sesame
        val sesameButton : Button = view.findViewById(R.id.sesameButton)
        sesameButton.setOnClickListener{
            val intent = Intent(requireContext(), AllergyList::class.java)
            intent.putExtra("key", "Sesame")
            startActivity(intent)
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllergyOptionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllergyOption().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}