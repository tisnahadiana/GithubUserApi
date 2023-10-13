package id.tisnahadiana.githubuserapi.core.source.remote.network

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.source.remote.response.GithubDetailResponse
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): SearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): GithubDetailResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<User>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<User>

}