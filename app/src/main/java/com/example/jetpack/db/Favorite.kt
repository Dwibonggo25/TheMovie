package com.example.jetpack.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favorite")
data class Favorite (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    val id : Int?,

    @ColumnInfo (name = "title")
    val title: String?,

    @ColumnInfo (name = "date_realesed")
    val dateReleased: String?,

    @ColumnInfo (name = "url_poster")
    val urlPoster: String?,

    @ColumnInfo (name = "vote")
    val vote: String?,

    @ColumnInfo (name = "original_title")
    val originalTitle: String?,

    @ColumnInfo (name = "overview")
    val overview: String?,

    @ColumnInfo (name = "type")
    val type: Int?
){
    constructor(): this(0,"","","","","","",0)
}