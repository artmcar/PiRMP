package com.example.pirmp_8_makaryanaa

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var requestQueue: RequestQueue
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        btn1.setOnClickListener{
            val firstFragment = FirstFragment()
            val secondFragment = SecondFragment()
            val thirdFragment = ThirdFragment()

            supportFragmentManager.beginTransaction().replace(R.id.frame_layout_1,firstFragment).addToBackStack(null).commit()
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout_2,secondFragment).addToBackStack(null).commit()
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout_3,thirdFragment).addToBackStack(null).commit()

        }
        btn2.setOnClickListener{
            val firstWorkRequest = OneTimeWorkRequestBuilder<FirstWorker>().build()
            val secondWorkRequest = OneTimeWorkRequestBuilder<SecondWorker>().build()
            WorkManager.getInstance(this).enqueue(listOf(firstWorkRequest, secondWorkRequest))
        }
        requestQueue = Volley.newRequestQueue(this)
        btn3.setOnClickListener { getDog() }
    }


    private fun getDog() {
        val url = "https://random.dog/woof.json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val imageUrl = response.getString("url")
                if (imageUrl.endsWith(".jpg") || imageUrl.endsWith(".jpeg") || imageUrl.endsWith(".png")) {
                    Glide.with(this)
                        .load(imageUrl)
                        .into(imageView)
                } else {
                    Toast.makeText(this, "Ой, эта собачка двигается", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Ошибка: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(jsonObjectRequest)
    }

}