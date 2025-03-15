package com.example.pirmp_4_2_makaryanaa

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn_fragment1 = findViewById<Button>(R.id.btn_fragment_1)
        var btn_fragment2 = findViewById<Button>(R.id.btn_fragment_2)
        var btn_fragment3 = findViewById<Button>(R.id.btn_fragment_3)

        btn_fragment1.setOnClickListener {
            val firstFragment = FirstFragment()
            setNewFragment(firstFragment)
        }
        btn_fragment2.setOnClickListener {
            val secondFragment = SecondFragment()
            setNewFragment(secondFragment)
        }

        btn_fragment3.setOnClickListener {
            val thirdFragment = ThirdFragment()
            setNewFragment(thirdFragment)
        }



    }
    fun setNewFragment(fragment: Fragment) {
        val ftransaction = supportFragmentManager.beginTransaction()
        ftransaction.replace(R.id.frame_layout, fragment)
        ftransaction.addToBackStack(null)
        ftransaction.commit()
    }
}