package com.example.capstoneproject.config

import com.example.capstoneproject.data.network.CriptoApiClient
import com.example.capstoneproject.data.network.CriptoEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(CriptoEndpoints.criptoUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): CriptoApiClient =
        retrofit.create(CriptoApiClient::class.java)

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor {
                val newRequest = it.request().newBuilder().addHeader("User-Agent", "Header1").build()
                it.proceed(newRequest)
            }
        okHttpClient.callTimeout(20, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS)
        okHttpClient.readTimeout(20, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(20, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }
}
