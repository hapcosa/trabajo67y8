package com.example.trabajo67y8.views;

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.trabajo67y8.component.MovieCard
import com.example.trabajo67y8.component.topBar
import com.example.trabajo67y8.viewmodel.MovieViewModel

@Composable
fun HomeView(navController: NavController, viewModel: MovieViewModel){
    Log.d("scan", "onCreate1")
    Scaffold(
        topBar={topBar("Inicio", navController)},
        content = { ContentHomeView(it,navController, viewModel)},
        bottomBar = {}
    )
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, viewModel: MovieViewModel) {
   LaunchedEffect(Unit){
       viewModel.getProducts()
   }
    val state = viewModel.movies.collectAsState(emptyList())
    Column(
        modifier = androidx.compose.ui.Modifier.padding(it)
    ) {
        LazyColumn(){
            items(state.value){ movie ->
                MovieCard(navController , movie , viewModel)
            }
        }
    }

}
