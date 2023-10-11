package id.tisnahadiana.githubuserapi.core.domain.usecase

import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import javax.inject.Inject

class GithubUserInteractor @Inject constructor(private val githubUserRepository: IGithubUserRepository) :
    GithubUserUseCase {

    override suspend fun getFollowers(username: String) =
        githubUserRepository.getFollowers(username)

    override suspend fun getFollowing(username: String) =
        githubUserRepository.getFollowing(username)

    override suspend fun getUserDetail(username: String) =
        githubUserRepository.getUserDetail(username)

    override suspend fun getSearchUsers(query: String) =
        githubUserRepository.getSearchUsers(query)

    override fun getFavoriteUser() =
        githubUserRepository.getFavoriteUser()

    override fun addToFavorite(
        username: String,
        id: Int,
        avatarUrl: String,
        htmlUrl: String
    ) = githubUserRepository.addToFavorite(username, id, avatarUrl, htmlUrl)

    override fun checkUser(id: Int): Int = githubUserRepository.checkUser(id)
    override fun removeFromFavorite(id: Int) = githubUserRepository.removeFromFavorite(id)

}