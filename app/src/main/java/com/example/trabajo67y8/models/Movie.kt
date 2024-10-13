package com.example.trabajo67y8.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey() var id: Int=0,
    val adult: Boolean?=false,
    val backdrop_path: String?="",
    val original_language: String?="",
    val original_title: String?="",
    val overview: String?="",
    val popularity: Double?=0.0,
    val poster_path: String?="",
    val release_date: String?="",
    val title: String?="",
    val video: Boolean?=false,
    val vote_average: Double?=0.0,
    val vote_count: Int?=0
)



