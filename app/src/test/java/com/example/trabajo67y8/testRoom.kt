package com.example.trabajo67y8

import com.example.trabajo67y8.data.MovieDao
import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.trabajo67y8.data.RestDataSource
import com.example.trabajo67y8.models.Movie
import com.example.trabajo67y8.repository.MovieRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

val movieuno = Movie(1, false, "audax", "audax", "audax", "audax", 3.9, "audax", "dsad", "audax")
val moviedos = Movie(2, false, "audax", "audax", "audax", "audax", 3.9, "audax", "dsad", "audax")

class testRoom {
    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = MyDispatcher
    }
    private val restDataSource = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestDataSource::class.java)
    private val newRepository = MovieRepositoryImp(restDataSource, MockMovieDao())

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `movie recibido correctamente segun Id`() {
        runBlocking {
            var movie = newRepository.gerProductDb(1)
            assert(movie.id == 1)
            assert(movie.title == "audax")
        }


    }


}


class MockMovieDao : MovieDao {
    private val movies = MutableLiveData(listOf(movieuno, moviedos))
    override fun getMovies(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getMovieById(movieId: Int): Movie {
        for (movie in movies.value!!) {
            if (movie.id == movieId) {
                return movie
            }
        }
        return Movie()
    }

    override suspend fun insert(movie: Movie) {
        movies.value = movies.value?.toMutableList()?.apply { add(movie) }
    }

    override suspend fun existsMovie(movieId: Int): Boolean {
        for (movie in movies.value!!) {
            if (movie.id == movieId) {
                return true
            }
        }
        return false
    }


}

val MyDispatcher: Dispatcher = object : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/equipos" -> MockResponse().apply { addResponse("clubs.json") }
            else -> MockResponse().setResponseCode(404)
        }
        MockResponse().apply { addResponse("clubs.json") }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader.getResourceAsStream(filePath)
    val source = inputStream.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}
