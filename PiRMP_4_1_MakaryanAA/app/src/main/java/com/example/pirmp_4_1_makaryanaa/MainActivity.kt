package com.example.pirmp_4_1_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_next = findViewById<Button>(R.id.nextButton)
        button_next.setOnClickListener{
            onNextActivity()
        }

    }

    fun onNextActivity(){

        val first_name = findViewById<EditText>(R.id.fname)
        val last_name = findViewById<EditText>(R.id.lname)

        val fname = first_name.text.toString()
        val lname = last_name.text.toString()

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("fname",fname)
        intent.putExtra("lname",lname)
        startActivity(intent)
    }
}