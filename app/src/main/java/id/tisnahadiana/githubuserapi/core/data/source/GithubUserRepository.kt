package id.tisnahadiana.githubuserapi.core.data.source

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import kotlinx.coroutines.flow.Flow

class GithubUserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGithubUserRepository {

    companion object {
        @Volatile
        private var instance: GithubUserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource
        ): GithubUserRepository =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(remoteData, localData)
            }
    }


    override suspend fun getFollowers(username: String): Flow<List<User>> =
        remoteDataSource.getFollowers(username)

    override suspend fun getFollowing(username: String): Flow<List<User>> =
        remoteDataSource.getFollowing(username)

    override suspend fun getUserDetail(username: String): Flow<GithubDetailResponse> =
        remoteDataSource.getUserDetail(username)

    override suspend fun getSearchUsers(query: String): Flow<List<User>> =
        remoteDataSource.getSearchUsers(query)

    override suspend fun getFavoriteUser(): Flow<List<FavoriteUser>> =
        localDataSource.getFavoriteUser()

    override suspend fun addToFavorite(
        username: String,
        id: Int,
        avatarUrl: String,
        htmlUrl: String
    ) = localDataSource.addToFavorite(username, id, avatarUrl, htmlUrl)

    override suspend fun checkUser(id: Int): Int = localDataSource.checkUser(id)
    override suspend fun removeFromFavorite(id: Int) = localDataSource.removeFromFavorite(id)
}