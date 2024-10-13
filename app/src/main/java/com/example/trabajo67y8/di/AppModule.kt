package com.example.trabajo67y8.di

import android.content.Context
import androidx.room.Room
import com.example.trabajo67y8.data.RestDataSource
import com.example.trabajo67y8.data.dataBaseMovie
import com.example.trabajo67y8.utils.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.trabajo67y8.data.MovieDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dataBaseMovie(@ApplicationContext context: Context): dataBaseMovie {
        return Room.databaseBuilder(
            context,
            dataBaseMovie::class.java,
            "movies_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun movieDao(db: dataBaseMovie):MovieDao = db.MovieDao()

}