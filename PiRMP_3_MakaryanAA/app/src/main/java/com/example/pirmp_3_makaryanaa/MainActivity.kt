package com.example.pirmp_3_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        val button1 = findViewById<Button>(R.id.nextButton)
        button1.setOnClickListener{
            onNextActivity()
        }
    }

    fun onNextActivity(){

        val groupText = findViewById<EditText>(R.id.group)
        val nameText = findViewById<EditText>(R.id.name)
        val ageText = findViewById<EditText>(R.id.age)

        val group = groupText.text.toString()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toIntOrNull() ?: 42

        val myObject = MyObject(group, name, age)
        val intent = Intent(this, LinearLayoutActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("myObject", myObject)
        startActivity(intent)
    }
}