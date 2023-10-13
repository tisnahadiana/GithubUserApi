package id.tisnahadiana.githubuserapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserInteractor
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGithubUserUseCase(githubUserInteractor: GithubUserInteractor): GithubUserUseCase
}