package id.tisnahadiana.githubuserapi.core.source

import id.tisnahadiana.githubuserapi.core.domain.model.Favorite
import id.tisnahadiana.githubuserapi.core.domain.model.GithubUserDetail
import id.tisnahadiana.githubuserapi.core.domain.model.Search
import id.tisnahadiana.githubuserapi.core.domain.model.User
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import id.tisnahadiana.githubuserapi.core.source.local.LocalDataSource
import id.tisnahadiana.githubuserapi.core.source.remote.RemoteDataSource
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubUserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGithubUserRepository {

    override suspend fun getFollowers(username: String): Flow<ApiResponse<List<User>>> =
        remoteDataSource.getFollowers(username)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val followers = apiResponse.data.map { userResponse ->
                            DataMapper.mapUserResponseToDomain(userResponse)
                        }
                        ApiResponse.Success(followers)
                    }
                    is ApiResponse.Error -> ApiResponse.Error(apiResponse.errorMessage)
                    is ApiResponse.Empty -> ApiResponse.Empty
                }
            }

    override suspend fun getFollowing(username: String): Flow<ApiResponse<List<User>>> =
        remoteDataSource.getFollowing(username)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val following = apiResponse.data.map { userResponse ->
                            DataMapper.mapUserResponseToDomain(userResponse)
                        }
                        ApiResponse.Success(following)
                    }
                    is ApiResponse.Error -> ApiResponse.Error(apiResponse.errorMessage)
                    is ApiResponse.Empty -> ApiResponse.Empty
                }
            }

    override suspend fun getUserDetail(username: String): Flow<ApiResponse<GithubUserDetail>> =
        remoteDataSource.getUserDetail(username)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val mappedUserDetail = DataMapper.mapGithubDetailResponseToUserDetail(apiResponse.data)
                        ApiResponse.Success(mappedUserDetail)
                    }
                    is ApiResponse.Error -> ApiResponse.Error(apiResponse.errorMessage)
                    is ApiResponse.Empty -> ApiResponse.Empty
                }
            }

    override suspend fun getSearchUsers(query: String): Flow<ApiResponse<Search>> =
        remoteDataSource.getSearchUsers(query)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val searchResult = DataMapper.mapSearchResponseToDomain(apiResponse.data)
                        ApiResponse.Success(searchResult)
                    }
                    is ApiResponse.Error -> ApiResponse.Error(apiResponse.errorMessage)
                    is ApiResponse.Empty -> ApiResponse.Empty
                }
            }

    override fun getFavoriteUser(): Flow<List<Favorite>> =
        localDataSource.getFavoriteUser()
            .map { entities ->
                entities.map { DataMapper.mapFavoriteEntityToDomain(it) }
            }

    override fun addToFavorite(
        username: String,
        id: Int,
        avatarUrl: String,
        htmlUrl: String
    ) {
        val entity = DataMapper.mapFavoriteToEntity(
            Favorite(username, id, avatarUrl, htmlUrl)
        )
        localDataSource.addToFavorite(entity.login, entity.id, entity.avatar_url, entity.html_url)
    }

    override fun checkUser(id: Int): Int = localDataSource.checkUser(id)
    override fun removeFromFavorite(id: Int) = localDataSource.removeFromFavorite(id)
}