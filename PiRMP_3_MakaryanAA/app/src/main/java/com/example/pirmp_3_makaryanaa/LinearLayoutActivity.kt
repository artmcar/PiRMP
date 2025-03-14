package com.example.pirmp_3_makaryanaa

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)

        val button2 = findViewById<Button>(R.id.backButton)
        val messageText = findViewById<TextView>(R.id.outText)

        val myObjectInput = intent.getSerializableExtra("myObject") as? MyObject

        if (myObjectInput != null) {
            messageText.text = "${getString(R.string.group_hint)}: ${myObjectInput.group}\n" +
                    "${getString(R.string.name_hint)}: ${myObjectInput.name}\n" +
                    "${getString(R.string.age_hint)}: ${myObjectInput.age}"
        }

        button2.setOnClickListener{
            finish()
        }
    }
}