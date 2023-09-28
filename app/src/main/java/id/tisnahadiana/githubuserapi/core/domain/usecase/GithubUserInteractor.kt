package id.tisnahadiana.githubuserapi.core.domain.usecase

import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.GithubUserRepository
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse

class GithubUserInteractor (private val githubUserRepository: GithubUserRepository) : GithubUserUseCase {

    fun getFollowers(username: String, callback: (List<User>?, String?) -> Unit) {
        githubUserRepository.getFollowers(username, callback)
    }

    fun getFollowing(username: String, callback: (List<User>?, String?) -> Unit) {
        githubUserRepository.getFollowing(username, callback)
    }

    fun getUserDetail(username: String, callback: (GithubDetailResponse?, String?) -> Unit) {
        githubUserRepository.getUserDetail(username, callback)
    }

    fun getSearchUsers(query: String, callback: (List<User>?, String?) -> Unit) {
        githubUserRepository.getSearchUsers(query, callback)
    }

}