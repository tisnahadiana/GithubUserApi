package id.tisnahadiana.githubuserapi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.data.source.GithubUserRepository
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: GithubUserRepository): IGithubUserRepository

}