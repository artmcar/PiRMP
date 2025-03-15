package com.example.pirmp_4_1_makaryanaa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResult


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textViewFname = findViewById<TextView>(R.id.get_fname)
        val textViewLname = findViewById<TextView>(R.id.get_lname)
        val editText = findViewById<EditText>(R.id.new_lesson)
        val enterButton = findViewById<Button>(R.id.enterButton)
        enterButton.setOnClickListener{
            val intent = Intent(this,ThirdActivity::class.java)
            val nlesson = editText.text.toString()
            intent.putExtra("nlesson",nlesson)

            mStartForResult.launch(intent)
        }

        val arguments = intent.extras
        if (arguments != null) {
            val fname = arguments.get("fname").toString()
            val lname = arguments.get("lname").toString()
            textViewFname.text = fname
            textViewLname.text = lname
        }


    }

    val mStartForResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val day_time_comment = result.data?.getStringExtra("DTC")
            Toast.makeText(this, "Дополнительное занятие: $day_time_comment", Toast.LENGTH_LONG).show()
        }
    }

}