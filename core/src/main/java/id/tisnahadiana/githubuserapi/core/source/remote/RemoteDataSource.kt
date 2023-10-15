package id.tisnahadiana.githubuserapi.core.source.remote

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.UserResponse
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiService
import id.tisnahadiana.githubuserapi.core.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getFollowers(username: String): Flow<ApiResponse<List<UserResponse>>> = flow {
        try {
            val followers = apiService.getFollowers(username)
            emit(ApiResponse.Success(followers))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getFollowing(username: String): Flow<ApiResponse<List<UserResponse>>> = flow {
        try {
            val following = apiService.getFollowing(username)
            emit(ApiResponse.Success(following))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUserDetail(username: String): Flow<ApiResponse<GithubDetailResponse>> = flow {
        try {
            val userDetail = apiService.getUserDetail(username)
            emit(ApiResponse.Success(userDetail))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getSearchUsers(query: String): Flow<ApiResponse<SearchResponse>> = flow {
        try {
            val searchResults = apiService.searchUsers(query)
            emit(ApiResponse.Success(searchResults))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)

}