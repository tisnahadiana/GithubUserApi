package id.tisnahadiana.githubuserapi.core.domain.repository

import androidx.lifecycle.LiveData
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse

interface IGithubUserRepository {
    fun getFollowers(username: String): LiveData<List<User>?>
    fun getFollowing(username: String): LiveData<List<User>?>
    fun getUserDetail(username: String): LiveData<GithubDetailResponse?>
    fun getSearchUsers(query: String): LiveData<List<User>?>
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>
    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String)
    fun checkUser(id: Int) : Int

    fun removeFromFavorite(id: Int)
}