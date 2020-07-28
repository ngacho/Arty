package com.example.jsonplaceholder.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey
    @ColumnInfo(name = "photo_id")
    val id: Int,
    @ColumnInfo(name = "photo_title")
    val title: String,
    @ColumnInfo(name = "photo_url")
    val url: String
)
