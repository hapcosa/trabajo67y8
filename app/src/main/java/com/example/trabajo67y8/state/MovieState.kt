package com.example.trabajo67y8.state

import com.example.trabajo67y8.models.Movie

data class MovieState(val movies:List<Movie> = emptyList(), val movie:Movie = Movie())