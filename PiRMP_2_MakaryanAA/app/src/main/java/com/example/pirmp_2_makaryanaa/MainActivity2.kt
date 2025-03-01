package com.example.pirmp_2_makaryanaa

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val messageText = TextView(this)
        messageText.textSize = 26f
        messageText.setPadding(16,16,16,16)

        val arguments = intent.extras
        if (arguments != null) {
            val fio = arguments.get("fio").toString()
            val groupnum = arguments.get("groupnum").toString()
            val age = arguments.getInt("age")
            val grade = arguments.getInt("grade")
            messageText.text = "FIO: $fio\nGroup Number: $groupnum\nAge: $age\nGrade: $grade"
        }
        setContentView(messageText)

    }
}