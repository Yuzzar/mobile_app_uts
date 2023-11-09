package com.example.apputs

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.apputs.databinding.ActivityProfilViewBinding

class profil_view : AppCompatActivity() {

    private lateinit var binding: ActivityProfilViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("Nama Lengkap")
        val message1 = intent.getStringExtra("First Name")
        val message2 = intent.getStringExtra("Last name")
        val message3 = intent.getStringExtra("Gender")
        val message4 = intent.getStringExtra("Phone Number")
        val message5 = intent.getStringExtra("Tanggal Lahir")


        val namalengkap = findViewById<TextView>(R.id.nama_lengkap).apply {

            text = message
        }
        val first = findViewById<TextView>(R.id.view_first_name).apply {

            text = message1
        }

        val gender = findViewById<TextView>(R.id.view_gender).apply {

            text = message3
        }
        val phone = findViewById<TextView>(R.id.view_phone).apply {

            text = message4
        }
        val Tanggal = findViewById<TextView>(R.id.tanggal).apply {

            text = message5
        }


        binding.btnEdit1.setOnClickListener {
            val intent = Intent(this, edit_profil::class.java)
            startActivity(intent)
        }

    }
}