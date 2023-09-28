package id.tisnahadiana.githubuserapi.core.di

import android.content.Context
import id.tisnahadiana.githubuserapi.core.data.source.GithubUserRepository
import id.tisnahadiana.githubuserapi.core.data.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.data.source.local.room.UserDatabase
import id.tisnahadiana.githubuserapi.core.data.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiConfig
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserInteractor
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): GithubUserRepository {
        val database = UserDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database!!.favoriteUserDao())
        val appExecutors = AppExecutors()

        return GithubUserRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): GithubUserUseCase {
        val repository = provideRepository(context)
        return GithubUserInteractor(repository)
    }

}