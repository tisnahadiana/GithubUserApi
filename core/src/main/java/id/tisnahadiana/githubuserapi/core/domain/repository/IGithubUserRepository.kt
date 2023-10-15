package id.tisnahadiana.githubuserapi.core.domain.repository

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.UserResponse
import id.tisnahadiana.githubuserapi.core.domain.model.Favorite
import id.tisnahadiana.githubuserapi.core.domain.model.GithubUserDetail
import id.tisnahadiana.githubuserapi.core.domain.model.Search
import id.tisnahadiana.githubuserapi.core.domain.model.User
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteEntity
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.flow.Flow

interface IGithubUserRepository {
    suspend fun getFollowers(username: String): Flow<ApiResponse<List<User>>>
    suspend fun getFollowing(username: String): Flow<ApiResponse<List<User>>>
    suspend fun getUserDetail(username: String): Flow<ApiResponse<GithubUserDetail>>
    suspend fun getSearchUsers(query: String): Flow<ApiResponse<Search>>
    fun getFavoriteUser(): Flow<List<Favorite>>
    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String)
    fun checkUser(id: Int): Int
    fun removeFromFavorite(id: Int)
}