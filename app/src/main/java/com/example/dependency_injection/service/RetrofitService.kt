package com.example.dependency_injection.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    /**
     * Cria uma instacia do retrofit passando a url base.
     * Deixamos como private pq não interessa para as outras classes, oq nos interessa
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    /**
     * Retorna a interface GithubServices implementada pelo retrofit.
     */
    fun getGithubServices(): GithubServices = retrofit.create(GithubServices::class.java)
}