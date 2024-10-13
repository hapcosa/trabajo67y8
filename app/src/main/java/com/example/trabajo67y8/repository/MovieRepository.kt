package com.example.trabajo67y8.repository

import android.util.Log
import com.example.trabajo67y8.data.RestDataSource
import com.example.trabajo67y8.data.MovieDao
import com.example.trabajo67y8.models.Movie
import com.example.trabajo67y8.models.MovieDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

interface MovieRepository {
    suspend fun getProduct(id: Int): Movie
    suspend fun getAllProducts(category:String, page:Int): ArrayList<Movie>
    fun getProductsDb(): Flow<List<Movie>>
    fun gerProductDb(id: Int): Movie
    suspend fun exists(id: Int): Boolean
}

class MovieRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val MovieDao: MovieDao
) : MovieRepository {
    override suspend fun getProduct(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(category:String, page:Int): ArrayList<Movie> {
        Log.d("scan", "onCreate4.")
        var listMovie: ArrayList<Movie> = ArrayList()
        val response: List<MovieDto> = try {
            dataSource.getMoviesList(category, page).results
        } catch (e: IOException) {
            Log.d("scan", "onCreateio ${e.message}")
            return listMovie


        } catch (e: HttpException) {
            Log.d("scan", "onCreate4http ${e.message()}")
            return listMovie


        }
        Log.d("scan", "onCreate4$response")
        var movie: Movie = Movie()

        var i: Int = 0
        for (result in response) {
            movie = Movie(i,result.adult,result.backdrop_path, result.original_language,result.original_title,result.overview,result.popularity,result.poster_path,result.release_date,result.title,result.video,result.vote_average,result.vote_count )
            i++
            MovieDao.insert(movie)
            listMovie.add(movie)
        }
        return listMovie
    }

    override fun getProductsDb(): Flow<List<Movie>> = MovieDao.getMovies()
    override fun gerProductDb(id: Int): Movie = MovieDao.getMovieById(id)

    override suspend fun exists(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}