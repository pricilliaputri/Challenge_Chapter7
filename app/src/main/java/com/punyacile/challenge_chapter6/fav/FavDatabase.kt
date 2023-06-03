package com.punyacile.challenge_chapter6.fav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataFavoriteMovie :: class], version = 1)
abstract class FavDatabase : RoomDatabase() {

    abstract fun favDao() : FavDao

    companion object{
        private var INSTANCE : FavDatabase? = null

        fun getInstance(context: Context) :FavDatabase? {
            if(INSTANCE == null){
                synchronized(FavDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavDatabase::class.java, "Fav.db"
                    )
                        .build()
                }

            }
            return INSTANCE
        }

    }
}