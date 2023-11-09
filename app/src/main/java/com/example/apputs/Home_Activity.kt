package com.example.apputs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apputs.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class Home_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(FKalkulator())

        binding.image.setOnClickListener {
            val intent = Intent(this, profil_view::class.java)
            startActivity(intent)
        }
        // definisi widget
        var bottomnav = findViewById<BottomNavigationView>(R.id.bottomnavview)
        bottomnav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.bot_menu_Calculator -> {
                    loadFragment(FKalkulator())
                    true
                }
                R.id.bot_menu_konversi -> {
                    loadFragment(mata_uang())
                    true
                }
                R.id.bot_menu_suhu -> {
                    loadFragment(FSuhu())
                    true
                }
                R.id.bot_menu_bmi -> {
                    loadFragment(FBmi())
                    true
                }

                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.f_container, fragment)
        transaction.commit()
    }
}