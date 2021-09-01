package com.example.dependency_injection.model

data class GithubUserResponse(
    val items: List<GithubUser>
)

data class GithubUser(
    val id: Int,
    val login: String
)
