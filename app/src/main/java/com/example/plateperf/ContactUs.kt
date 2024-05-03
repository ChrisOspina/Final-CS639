package com.example.plateperf

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactUs.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactUs : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact_us, container, false)

        val sendto = view.findViewById<EditText>(R.id.editTextEmail)
        val body = view.findViewById<EditText>(R.id.editTextMessage)
        val button = view.findViewById<Button>(R.id.sendEmailButton)

        // attach setOnClickListener to button with Intent object define in it
        button.setOnClickListener {
            val emailsend = sendto.getText().toString()
            val emailbody = body.getText().toString()

            // define Intent object with action attribute as ACTION_SEND
            val intent = Intent(Intent.ACTION_SEND)

            // add three fields to intent using putExtra function
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailsend))
            intent.putExtra(Intent.EXTRA_TEXT, emailbody)

            // set type of intent
            intent.type = "message/rfc822"

            // startActivity with intent with chooser as Email client using createChooser function
            startActivity(Intent.createChooser(intent, "Choose an Email client :"))
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
         * @return A new instance of fragment ContactUs.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactUs().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}