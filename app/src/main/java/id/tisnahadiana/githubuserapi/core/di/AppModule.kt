package id.tisnahadiana.githubuserapi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserInteractor
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(tourismInteractor: GithubUserInteractor): GithubUserUseCase
}