package id.tisnahadiana.githubuserapi.core.domain.usecase

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
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

}