package com.example.pirmp_6_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        setSupportActionBar(toolbar)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Главная"

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.nav_poet1 -> setNewFragment(Fragment1())
                R.id.nav_poet2 -> setNewFragment(Fragment2())
                R.id.nav_poet3 -> setNewFragment(Fragment3())
                R.id.nav_poet4 -> setNewFragment(Fragment4())
                R.id.nav_next_activity -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                }
            }
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }
        else super.onOptionsItemSelected(item)
    }
    fun setNewFragment(fragment: Fragment) {
        val ftransaction = supportFragmentManager.beginTransaction()
        ftransaction.replace(R.id.content_frame, fragment)
        ftransaction.addToBackStack(null)
        ftransaction.commit()
    }
}