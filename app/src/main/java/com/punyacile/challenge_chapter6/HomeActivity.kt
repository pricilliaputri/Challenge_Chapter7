package com.punyacile.challenge_chapter6

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.challenge_chapter6.adapter.MovieAdapter
import com.punyacile.challenge_chapter6.databinding.ActivityHomeBinding
import com.punyacile.challenge_chapter6.model.DataPopularMovie
import com.punyacile.challenge_chapter6.viewmodel.MovieViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var pref: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = this.getSharedPreferences("data", Context.MODE_PRIVATE)

        binding.imageView.setOnClickListener {
            val save = pref.edit()
            save.putString("username", "")
            save.apply()
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        val showUsername = pref.getString("username", "username")
        binding.welcome.text = "Welcome, $showUsername"

        val viewModelMovie = ViewModelProvider(this)[MovieViewmodel::class.java]
        viewModelMovie.getMovie()
        viewModelMovie.liveDataMovie.observe(this) {
            if (it != null) {
                binding.rcvcon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rcvcon.adapter = MovieAdapter(it, this@HomeActivity)
            }
        }
        binding.homeFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
            finish()
        }
    }
    override fun onItemClick(data: DataPopularMovie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data_detail", data)
        startActivity(intent)
    }
}