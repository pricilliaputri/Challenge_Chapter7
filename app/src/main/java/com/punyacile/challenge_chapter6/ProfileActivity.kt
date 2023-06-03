package com.punyacile.challenge_chapter6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.punyacile.challenge_chapter6.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("data", Context.MODE_PRIVATE)

        binding.btnUpdate.setOnClickListener {
            val getUsername = binding.user.text.toString()
            val save = sharedPreferences.edit()
            save.putString("username", getUsername)
            save.apply()
            startActivity(Intent(this, HomeActivity::class.java))
            Toast.makeText(this, "Profile Update Success", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnLogout.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}