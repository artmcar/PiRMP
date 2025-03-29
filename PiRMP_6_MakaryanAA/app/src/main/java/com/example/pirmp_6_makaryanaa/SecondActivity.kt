package com.example.pirmp_6_makaryanaa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Вторая активность"

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener{menuItem ->
            when (menuItem.itemId) {
                R.id.nav_bottom_first -> {
                    setNewFragment(Fragment5())
                    supportActionBar?.title = "Draw"
                    true
                }
                R.id.nav_bottom_second -> {
                    setNewFragment(Fragment6())
                    supportActionBar?.title = "Paint"
                    true
                }
                R.id.nav_bottom_third -> {
                    setNewFragment(Fragment7())
                    supportActionBar?.title = "Color"
                    true
                }
                else -> false
            }

        }
    }
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun setNewFragment(fragment: Fragment) {
        val ftransaction = supportFragmentManager.beginTransaction()
        ftransaction.replace(R.id.content_frame2, fragment)
        ftransaction.addToBackStack(null)
        ftransaction.commit()
    }
}