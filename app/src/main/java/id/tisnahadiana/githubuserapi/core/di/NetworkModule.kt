package id.tisnahadiana.githubuserapi.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiConfig
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiConfig.getApiService()

}