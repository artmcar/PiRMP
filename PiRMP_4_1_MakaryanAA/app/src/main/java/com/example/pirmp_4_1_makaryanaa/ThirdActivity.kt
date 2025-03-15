package com.example.pirmp_4_1_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val editTextDay = findViewById<EditText>(R.id.day)
        val editTextTime = findViewById<EditText>(R.id.time)
        val editTextComment = findViewById<EditText>(R.id.comment)
        val ok_button = findViewById<Button>(R.id.ok_button)

        val nlesson = intent.getStringExtra("nlesson")

        ok_button.setOnClickListener{
            val day = editTextDay.text.toString()
            val time = editTextTime.text.toString()
            val comment = editTextComment.text.toString()
            val day_time_comment = "$nlesson: $day, $time, $comment"

            val intent = Intent().apply {
                putExtra("DTC", day_time_comment)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}