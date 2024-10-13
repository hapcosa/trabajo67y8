package com.example.trabajo67y8.viewmodel;
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.trabajo67y8.data.RestDataSource
import com.example.trabajo67y8.models.Movie
import com.example.trabajo67y8.repository.MovieRepository
import com.example.trabajo67y8.state.MovieState
import com.example.trabajo67y8.utils.MyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val Repo: MovieRepository,
    private val restDataSource: RestDataSource
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    var state by mutableStateOf(MovieState())
        private set

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = Repo.getAllProducts("popular",1)
                _movies.value = result ?: emptyList()
            }
        }
    }

    val pager = Pager(
        config = PagingConfig(pageSize = 1),
        pagingSourceFactory = { MyPagingSource(restDataSource) }
    )



    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    Repo.getAllProducts("popular",1)
                    Log.d("scan", "onCreate3")

                }
            }
        }


    }
    fun getProductById(id: Int) {
        Log.d("scan", "onCreate3")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = Repo.getProduct(id)
                state = state.copy(


                )
            }
        }
    }

    fun clean() {
        state = state.copy(
            movie= Movie()

        )
    }
}