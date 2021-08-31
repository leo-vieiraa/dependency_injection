package com.example.dependency_injection.service

import com.example.dependency_injection.model.GithubRepositoryResponse
import com.example.dependency_injection.model.GithubUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubServices {

    /**
     * Usamos o @Query para deixar os paremetros dinamicos de acordo com a documentacao da API
     */
    @GET("/search/repositories")
    fun fetchRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Call<GithubRepositoryResponse>

    @GET("/users")
    fun fetchUsers() : Call<GithubUserResponse>

}