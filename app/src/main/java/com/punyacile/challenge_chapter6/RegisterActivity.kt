package com.punyacile.challenge_chapter6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.punyacile.challenge_chapter6.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        pref = this.getSharedPreferences("data", Context.MODE_PRIVATE)

        binding.registerLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        binding.buttonRegister.setOnClickListener {
            val email = binding.emailRegist.text.toString()
            val password = binding.passRegist.text.toString()
            val getUsername = binding.usernameRegist.text.toString()

            if (password.isEmpty()) {
                binding.passRegist.error = "Insert password"
                binding.passRegist.requestFocus()
                return@setOnClickListener
            }

            firebaseRegisterAuth(email, password)
            val save = pref.edit()
            save.putString("username", getUsername)
            save.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun firebaseRegisterAuth(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}