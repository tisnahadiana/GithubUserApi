package id.tisnahadiana.githubuserapi.core.data.source.remote

import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiService
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getFollowers(username: String): Flow<List<User>> = apiService.getFollowers(username)

    suspend fun getFollowing(username: String): Flow<List<User>> = apiService.getFollowing(username)

    suspend fun getUserDetail(username: String): Flow<GithubDetailResponse> =
        apiService.getUserDetail(username)

    suspend fun getSearchUsers(query: String): Flow<List<User>> = apiService.searchUsers(query)

}