package id.tisnahadiana.githubuserapi.core.data.source

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGithubUserRepository {

    override suspend fun getFollowers(username: String): Flow<ApiResponse<List<User>>> {
        return remoteDataSource.getFollowers(username)
    }

    override suspend fun getFollowing(username: String): Flow<ApiResponse<List<User>>> =
        remoteDataSource.getFollowing(username)

    override suspend fun getUserDetail(username: String): Flow<ApiResponse<GithubDetailResponse>> =
        remoteDataSource.getUserDetail(username)

    override suspend fun getSearchUsers(query: String): Flow<ApiResponse<SearchResponse>> =
        remoteDataSource.getSearchUsers(query)

    override fun getFavoriteUser(): Flow<List<FavoriteUser>> =
        localDataSource.getFavoriteUser()

    override fun addToFavorite(
        username: String,
        id: Int,
        avatarUrl: String,
        htmlUrl: String
    ) = localDataSource.addToFavorite(username, id, avatarUrl, htmlUrl)

    override fun checkUser(id: Int): Int = localDataSource.checkUser(id)
    override fun removeFromFavorite(id: Int) = localDataSource.removeFromFavorite(id)
}