package com.example.themoviedb.app.di

import android.app.Application
import androidx.room.Room
import com.example.themoviedb.BuildConfig
import com.example.themoviedb.app.data.database.MovieDao
import com.example.themoviedb.app.data.network.TheMovieBdService
import com.example.themoviedb.app.data.database.TheMovieDataBase
import com.example.themoviedb.data.MainRepositoryImpl
import com.example.themoviedb.domain.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val initialRequest = chain.request()

            val newUrl = initialRequest.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val newRequest = initialRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMovieBdService(retrofit: Retrofit): TheMovieBdService {
        return retrofit.create(TheMovieBdService::class.java)
    }

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app, TheMovieDataBase::class.java, "themovie-db"
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(dataBase: TheMovieDataBase) = dataBase.movieDao()

    @Provides
    @Singleton
    fun provideMainRepository(service: TheMovieBdService, movieDao: MovieDao): MainRepository {
        return MainRepositoryImpl(service, movieDao)
    }
}