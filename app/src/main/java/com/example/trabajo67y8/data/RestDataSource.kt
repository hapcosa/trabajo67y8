package com.example.trabajo67y8.data


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.trabajo67y8.utils.Constant.Companion.API_KEY
import com.example.trabajo67y8.utils.Constant.Companion.ENDPOINT
import com.example.trabajo67y8.models.ApiResponse
interface RestDataSource {
    @GET(ENDPOINT+"{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): ApiResponse
}