package com.punyacile.challenge_chapter6

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.punyacile.challenge_chapter6.adapter.MovieAdapter
import com.punyacile.challenge_chapter6.databinding.ActivityDetailBinding
import com.punyacile.challenge_chapter6.fav.DataFavoriteMovie
import com.punyacile.challenge_chapter6.fav.FavDatabase
import com.punyacile.challenge_chapter6.model.DataPopularMovie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION", "DeferredResultUnused")
@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var binding: ActivityDetailBinding
    private var favDb: FavDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataPopularMovie>("data_detail")
        setViewData(data)

        favDb = FavDatabase.getInstance(this)

        binding.buttonFavorite.setOnClickListener {
            addDataFav(data)
            Toast.makeText(this, "Success add to Favorite", Toast.LENGTH_SHORT).show()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun addDataFav(data: DataPopularMovie?) {
        GlobalScope.async {
            val title = data?.title.toString()
            val date = data?.date.toString()
            val overview = data?.desc.toString()
            val imagepath = data?.image.toString()

            favDb?.favDao()?.insertData(DataFavoriteMovie(0, title, date, overview, imagepath))
        }
        finish()
    }

    private fun setViewData(data: DataPopularMovie?) {
        val title = data?.title
        val date = data?.date
        val overview = data?.desc
        val imagepath = data?.image

        binding.judul.text = title
        binding.Tanggal.text = date
        binding.Description.text = overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w780$imagepath").into(binding.imageView2)
    }

    override fun onItemClick(data: DataPopularMovie) {
        val move = Intent(this, DetailActivity::class.java)
        move.putExtra("data_detail", data)
        startActivity(move)
    }
}