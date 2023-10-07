package id.tisnahadiana.githubuserapi.core.data.source.remote

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiService
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.flow.Flow

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getFollowers(username: String): Flow<List<User>> = apiService.getFollowers(username)

    suspend fun getFollowing(username: String): Flow<List<User>> = apiService.getFollowing(username)

    suspend fun getUserDetail(username: String): Flow<GithubDetailResponse> =
        apiService.getUserDetail(username)

    suspend fun getSearchUsers(query: String): Flow<List<User>> = apiService.searchUsers(query)

}