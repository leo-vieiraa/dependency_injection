package com.example.dependency_injection.di

import com.example.dependency_injection.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideGithubRepository() : GithubRepository = GithubRepository()

}