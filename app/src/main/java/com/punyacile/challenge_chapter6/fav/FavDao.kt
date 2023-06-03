package com.punyacile.challenge_chapter6.fav

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Insert
    fun insertData(favData: DataFavoriteMovie)

    @Query("SELECT * FROM DataFavoriteMovie")
    fun getDataNote() : List<DataFavoriteMovie>

    @Delete
    fun deleteFav(dataFavorite : DataFavoriteMovie)
}