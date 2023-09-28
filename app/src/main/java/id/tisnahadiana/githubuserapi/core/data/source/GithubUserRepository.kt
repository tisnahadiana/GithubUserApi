package id.tisnahadiana.githubuserapi.core.data.source

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

    fun getFollowers(username: String, callback: (List<User>?, String?) -> Unit) {
        remoteDataSource.getFollowers(username, callback)
    }

    fun getFollowing(username: String, callback: (List<User>?, String?) -> Unit) {
        remoteDataSource.getFollowing(username, callback)
    }

    fun getUserDetail(username: String, callback: (GithubDetailResponse?, String?) -> Unit) {
        remoteDataSource.getUserDetail(username, callback)
    }

    fun getSearchUsers(query: String, callback: (List<User>?, String?) -> Unit) {
        remoteDataSource.getSearchUsers(query, callback)
    }
}