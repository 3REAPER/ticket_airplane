package ru.pervukhin.ticketairplane.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.pervukhin.data.ApiService
import ru.pervukhin.data.RepositoryImpl
import ru.pervukhin.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InternetModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder().baseUrl("https://cdn.rawgit.com/3REAPER/ticket_airplane/main/json/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: ApiService): Repository = RepositoryImpl(service)
}