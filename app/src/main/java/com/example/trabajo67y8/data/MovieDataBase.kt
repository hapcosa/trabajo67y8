package com.example.trabajo67y8.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trabajo67y8.models.Movie

@Database(
    entities = [Movie::class],
    version = 1
)

abstract class dataBaseMovie: RoomDatabase() {
    abstract fun MovieDao(): MovieDao

}