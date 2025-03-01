package com.example.pirmp_2_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        Log.d("cPiRMP", "onCreate")
        val Button1 = findViewById<Button>(R.id.button1)
        Button1.setOnClickListener{
            navigateToNextActivity()
        }

    }

    fun navigateToNextActivity(){
        val fio = findViewById<EditText>(R.id.fio)
        val groupnum = findViewById<EditText>(R.id.groupnum)
        val age = findViewById<EditText>(R.id.age)
        val grade = findViewById<EditText>(R.id.grade)
        val pfio = fio.text.toString()
        val pgroupnum = groupnum.text.toString()
        val page = age.text.toString().toIntOrNull() ?: 0
        val pgrade = grade.text.toString().toIntOrNull() ?: 0

        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("fio",pfio)
        intent.putExtra("groupnum",pgroupnum)
        intent.putExtra("age",page)
        intent.putExtra("grade",pgrade)
        startActivity(intent)
    }

    fun onNextActivity(view: View){
        navigateToNextActivity()
    }

    override fun onStart() {
        super.onStart()
        Log.d("cPiRMP", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("cPiRMP", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("cPiRMP", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("cPiRMP", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("cPiRMP", "onDestroy")
    }
}