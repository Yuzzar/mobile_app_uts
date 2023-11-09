package com.example.apputs

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apputs.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Initialize the binding object
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.forgot.setOnClickListener {
            val intent = Intent(this, Forgot_pass::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            val email = binding.signInEmail.text.toString()
            val pass = binding.signInPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, Home_Activity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
