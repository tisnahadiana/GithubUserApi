package id.tisnahadiana.githubuserapi.core.domain.usecase

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository

class GithubUserInteractor (private val githubUserRepository: IGithubUserRepository) : GithubUserUseCase {

    override fun getFollowers(username: String): LiveData<List<User>?> {
        return githubUserRepository.getFollowers(username)
    }

    override fun getFollowing(username: String): LiveData<List<User>?> {
        return githubUserRepository.getFollowing(username)
    }

    override fun getUserDetail(username: String) : LiveData<GithubDetailResponse?> {
        return githubUserRepository.getUserDetail(username)
    }

    override fun getSearchUsers(query: String): LiveData<List<User>?> {
        return githubUserRepository.getSearchUsers(query)
    }

    override fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return githubUserRepository.getFavoriteUser()
    }

    override fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        return githubUserRepository.addToFavorite(username, id, avatarUrl, htmlUrl)
    }

    override fun checkUser(id: Int) : Int  {
        return githubUserRepository.checkUser(id)
    }

    override fun removeFromFavorite(id: Int) {
        return githubUserRepository.removeFromFavorite(id)
    }

}