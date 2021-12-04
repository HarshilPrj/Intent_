package com.example.intent_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    lateinit var txtEmail:TextView
    lateinit var txtPass:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var email = intent.getStringExtra("email")
        var Pass = intent.getStringExtra("pass")

        txtEmail = findViewById(R.id.txtEmail)
        txtPass = findViewById(R.id.txtPass)

        txtEmail.setText(email)
        txtPass.setText(Pass)

        Log.d("SecondActivity","Email:$email")
        Log.d("SecondActivity","Password:$Pass")
    }
}