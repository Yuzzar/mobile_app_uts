    package com.example.apputs

    import android.content.Intent
    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import com.example.apputs.databinding.ActivityEditProfilBinding

    class edit_profil : AppCompatActivity() {

        private lateinit var binding: ActivityEditProfilBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityEditProfilBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnSave.setOnClickListener {
                callActivity()

            }
        }

        private fun callActivity(){
            val message = binding.namaLengkap1.text.toString()
            val message1 = binding.FirstName.text.toString()

            val message3 = binding.gender.text.toString()
            val message4 = binding.PhoneNumber.text.toString()
            val message5 = binding.tanggal1.text.toString()

            val intent = Intent(this, profil_view::class.java).also {
                it.putExtra("Nama Lengkap", message)
                it.putExtra("First Name", message1)

                it.putExtra("Gender", message3)
                it.putExtra("Phone Number", message4)
                it.putExtra("Tanggal Lahir", message5)
                startActivity(it)
            }

        }
    }