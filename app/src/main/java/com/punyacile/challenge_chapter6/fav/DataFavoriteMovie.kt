package com.punyacile.challenge_chapter6.fav

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class DataFavoriteMovie (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var date: String,
    var desc: String,
    var image: String,
) : Parcelable