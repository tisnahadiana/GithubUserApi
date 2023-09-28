package id.tisnahadiana.githubuserapi.core.domain.repository

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse

interface IGithubUserRepository {
    fun getFollowers(username: String): LiveData<List<User>?>
    fun getFollowing(username: String): LiveData<List<User>?>
    fun getUserDetail(username: String): LiveData<GithubDetailResponse?>
    fun getSearchUsers(query: String): LiveData<List<User>?>
}