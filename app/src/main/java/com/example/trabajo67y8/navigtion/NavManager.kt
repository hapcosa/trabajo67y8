package com.example.trabajo67y8.navigtion

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trabajo67y8.viewmodel.MovieViewModel
import com.example.trabajo67y8.views.DetailView
import com.example.trabajo67y8.views.HomeView

@Composable
fun NavManager(viewModel: MovieViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, viewModel)
        }
        composable(
            "DetailView/{id}{name}", arguments = listOf(
                navArgument("id") { type = NavType.IntType })

        ) {
            val id = it.arguments!!.getInt("id")
            DetailView(navController, viewModel, id)
        }
    }
}