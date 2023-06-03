package com.punyacile.challenge_chapter6

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.punyacile.challenge_chapter6.databinding.ActivityMainBinding

@Suppress(
    "MemberVisibilityCanBePrivate"
)
class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     lateinit var auth: FirebaseAuth
     lateinit var pref: SharedPreferences



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = this.getSharedPreferences("data_reg", Context.MODE_PRIVATE)

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))


        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()
        binding.buttonLgn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Invalid Email"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Fill the password"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            }
            firebaseAuthLogin(email, password)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun firebaseAuthLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Successfully, thank you!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed, try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}