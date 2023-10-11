package id.tisnahadiana.githubuserapi.core.domain.usecase

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.flow.Flow

interface GithubUserUseCase {
    suspend fun getFollowers(username: String): Flow<ApiResponse<List<User>>>
    suspend fun getFollowing(username: String): Flow<ApiResponse<List<User>>>
    suspend fun getUserDetail(username: String): Flow<ApiResponse<GithubDetailResponse>>
    suspend fun getSearchUsers(query: String): Flow<ApiResponse<SearchResponse>>
    fun getFavoriteUser(): Flow<List<FavoriteUser>>
    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String)
    fun checkUser(id: Int): Int
    fun removeFromFavorite(id: Int)
}