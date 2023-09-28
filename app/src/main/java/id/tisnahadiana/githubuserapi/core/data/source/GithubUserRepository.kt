package id.tisnahadiana.githubuserapi.core.data.source

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import id.tisnahadiana.githubuserapi.core.utils.AppExecutors

class GithubUserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubUserRepository {

    companion object {
        @Volatile
        private var instance: GithubUserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): GithubUserRepository =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getFollowers(username: String): LiveData<List<User>?> {
        return remoteDataSource.getFollowers(username)
    }

    override fun getFollowing(username: String): LiveData<List<User>?> {
        return remoteDataSource.getFollowing(username)
    }

    override fun getUserDetail( username: String ): LiveData<GithubDetailResponse?> {
        return remoteDataSource.getUserDetail(username)
    }

    override fun getSearchUsers(query: String): LiveData<List<User>?> {
        return remoteDataSource.getSearchUsers(query)
    }
}