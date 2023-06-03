package com.punyacile.challenge_chapter6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.punyacile.challenge_chapter6.FavoriteActivity
import com.punyacile.challenge_chapter6.databinding.FavListBinding
import com.punyacile.challenge_chapter6.fav.DataFavoriteMovie
import com.punyacile.challenge_chapter6.fav.FavDatabase
import com.punyacile.challenge_chapter6.model.DataPopularMovie
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DeferredResultUnused")
class FavoriteAdapter(private var listFavorite: List<DataFavoriteMovie>?, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var dbFav : FavDatabase? = null

    interface OnItemClickListener {
        fun onItemClick(data: DataPopularMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FavListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleFavorite.text = listFavorite!![position].title
        holder.binding.dateFavorite.text = listFavorite!![position].date
        holder.binding.dscFavorite.text = listFavorite!![position].desc
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listFavorite!![position].image}")
            .into(holder.binding.imgFav)

        holder.itemView.setOnClickListener {
            val image = listFavorite!![position].image
            val title = listFavorite!![position].title
            val date = listFavorite!![position].date
            val desc = listFavorite!![position].desc
            val detailFav = DataPopularMovie(image, title, date, desc)

            listener.onItemClick(detailFav)
        }
        holder.binding.deleteFav.setOnClickListener {
            dbFav = FavDatabase.getInstance(it.context)
            GlobalScope.async {
                dbFav?.favDao()?.deleteFav(listFavorite!![position])
                (holder.itemView.context as FavoriteActivity).runOnUiThread{
                    (holder.itemView.context as FavoriteActivity).getDataFav()
                }

            }
        }

    }

    override fun getItemCount(): Int {
        return listFavorite!!.size
    }

    inner class ViewHolder(val binding: FavListBinding) : RecyclerView.ViewHolder(binding.root)
}