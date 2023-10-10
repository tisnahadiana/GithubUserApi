package id.tisnahadiana.githubuserapi.core.domain.usecase

import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUserInteractor @Inject constructor(private val githubUserRepository: IGithubUserRepository) :
    GithubUserUseCase {

    override suspend fun getFollowers(username: String): Flow<List<User>> =
        githubUserRepository.getFollowers(username)

    override suspend fun getFollowing(username: String): Flow<List<User>> =
        githubUserRepository.getFollowing(username)

    override suspend fun getUserDetail(username: String): Flow<GithubDetailResponse> =
        githubUserRepository.getUserDetail(username)

    override suspend fun getSearchUsers(query: String): Flow<List<User>> =
        githubUserRepository.getSearchUsers(query)

    override suspend fun getFavoriteUser(): Flow<List<FavoriteUser>> =
        githubUserRepository.getFavoriteUser()

    override suspend fun addToFavorite(
        username: String,
        id: Int,
        avatarUrl: String,
        htmlUrl: String
    ) = githubUserRepository.addToFavorite(username, id, avatarUrl, htmlUrl)

    override suspend fun checkUser(id: Int): Int = githubUserRepository.checkUser(id)
    override suspend fun removeFromFavorite(id: Int) = githubUserRepository.removeFromFavorite(id)

}