package id.tisnahadiana.githubuserapi.core.domain.usecase

import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import kotlinx.coroutines.flow.Flow

interface GithubUserUseCase {
    suspend fun getFollowers(username: String): Flow<List<User>>
    suspend fun getFollowing(username: String): Flow<List<User>>
    suspend fun getUserDetail(username: String): Flow<GithubDetailResponse>
    suspend fun getSearchUsers(query: String): Flow<List<User>>
    suspend fun getFavoriteUser(): Flow<List<FavoriteUser>>
    suspend fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String)
    suspend fun checkUser(id: Int): Int
    suspend fun removeFromFavorite(id: Int)
}