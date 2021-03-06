package com.example.dependency_injection.repository

import com.example.dependency_injection.model.GithubRepositoryResponse
import com.example.dependency_injection.model.GithubUserResponse
import com.example.dependency_injection.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repositorio, será responsavel na interaçao com os dados que vem do Server/DAO/Firebase etc.
 * Netsa camada é onde criamos todos as funcoes precisam buscar dados, independente da sua fonte.
 */
class GithubRepository() {

    /**
     * Buscamos nossa Interface implementada do retrofit
     */
    private val githubServices = RetrofitService.getGithubServices()

    /**
     * Vamos expor o serviço de fetchRepositories para as outras camadas.
     */
    fun fetchRepositories(
        language: String,
        sort: String = "stars",
        page: Int = 1,
        onComplete: (GithubRepositoryResponse?, String?) -> Unit
    ) {
        val call = githubServices.fetchRepositories(
            language = "language:$language",
            sort = sort,
            page = page
        )
        call.enqueue(object : Callback<GithubRepositoryResponse> {
            override fun onResponse(
                call: Call<GithubRepositoryResponse>,
                response: Response<GithubRepositoryResponse>
            ) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Erro diferente")
                }
            }

            override fun onFailure(call: Call<GithubRepositoryResponse>, t: Throwable) {
                onComplete(null, t.message)
            }
        })

    }

    fun fetchUsers(onComplete: (GithubUserResponse?, String?) -> Unit) {

        val call = githubServices.fetchUsers()

        call.enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(
                call: Call<GithubUserResponse>,
                response: Response<GithubUserResponse>
            ) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Erro diferente XD")
                }
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                onComplete(null, t.message)
            }

        })

    }

    fun insertIntoDb() {

    }

    fun fetchRepositoriesFromDb(language: String) {

    }


}
