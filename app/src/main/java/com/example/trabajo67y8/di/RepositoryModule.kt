package com.example.trabajo67y8.di

import com.example.trabajo67y8.repository.MovieRepository
import com.example.trabajo67y8.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Android Devs Academy (Ahmed Guedmioui)
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun MovieListRepository(repositoryImp: MovieRepositoryImp): MovieRepository


}