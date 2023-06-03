package com.punyacile.challenge_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.punyacile.challenge_chapter6.databinding.MovieListBinding
import com.punyacile.challenge_chapter6.model.DataPopularMovie
import com.punyacile.challenge_chapter6.model.Result

class MovieAdapter(private var listMovie: List<Result>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: DataPopularMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleMovie.text = listMovie[position].originalTitle
        holder.binding.releaseMovie.text = listMovie[position].releaseDate
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listMovie[position].posterPath}")
            .into(holder.binding.imgMovie)

        holder.itemView.setOnClickListener {
            val image = listMovie[position].backdropPath
            val title = listMovie[position].title
            val date = listMovie[position].releaseDate
            val desc = listMovie[position].overview
            val detailData = DataPopularMovie(image, title, date, desc)

            listener.onItemClick(detailData)
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    inner class ViewHolder(val binding: MovieListBinding) : RecyclerView.ViewHolder(binding.root)
}