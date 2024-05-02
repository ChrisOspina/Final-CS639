package com.example.plateperf

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.plateperf.R

class ContactUsActivity : AppCompatActivity() {

    // define objects for edit text and button
    private lateinit var button: Button
    private lateinit var sendto: EditText
    private lateinit var body: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_contact_us)

        // Getting instance of edittext and button
        sendto = findViewById(R.id.editTextEmail)
        body = findViewById(R.id.editTextMessage)
        button = findViewById(R.id.sendEmailButton)

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
    }
}