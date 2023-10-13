package id.tisnahadiana.githubuserapi.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun githubUserUseCase(): GithubUserUseCase
}