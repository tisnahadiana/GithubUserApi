package id.tisnahadiana.githubuserapi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import id.tisnahadiana.githubuserapi.core.source.GithubUserRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: GithubUserRepository): IGithubUserRepository

}