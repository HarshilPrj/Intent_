package com.example.intent_view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var btnlogin: Button
    lateinit var etdemail: EditText
    lateinit var etdpwd: EditText
    lateinit var btnsemail: Button
    lateinit var btncamera: Button
    lateinit var btncall: Button
    lateinit var btnLweb: Button
    lateinit var btncsms: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnlogin = findViewById(R.id.btnlogin)
        etdemail = findViewById(R.id.etdemail)
        etdpwd = findViewById(R.id.etdpwd)
        btnsemail = findViewById(R.id.btnSemail)
        btncamera = findViewById(R.id.btncamera)
        btncall = findViewById(R.id.btncall)
        btnLweb = findViewById(R.id.btnLweb)
        btncsms = findViewById(R.id.btnCsms)
    }

    override fun onStart() {
        super.onStart()

        btnlogin.setOnClickListener {
            var intent: Intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("email", etdemail.text.toString())
            intent.putExtra("pass", etdpwd.text.toString())
            startActivity(intent)
        }

        btnsemail.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND)
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("harshilprajapati625@gmail.com", "hellypatel270798@gail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "DEMO OF SEND EMAIL")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello!, How Are You??")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Chose For Email"));
            }
        }

        btnLweb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        btncamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "open Camera"))
            }
        }

        btncsms.setOnClickListener {
            val smsUri = Uri.parse("tel:$9824131485")
            val intent = Intent(Intent.ACTION_VIEW, smsUri)
            intent.putExtra("address", "9427547066")
            intent.putExtra("sms_body","Hello")
            intent.type = "vnd.android-dir/mms-sms" // here setType will set the previous data to null.

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        btncall.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9173411423")
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestForPermission()
            } else {
                startActivity(intent)
            }
        }
    }
        private fun requestForPermission() {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        }

        override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 1) {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

