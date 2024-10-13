package com.example.trabajo67y8.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trabajo67y8.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
     fun getMovies(): Flow<List<Movie>>
    @Query("SELECT * FROM movies WHERE id = :movieId")
     fun getMovieById(movieId: Int): Movie
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)
    @Query("SELECT EXISTS(SELECT * FROM movies WHERE id = :movieId)")
    suspend fun existsMovie(movieId: Int): Boolean


}