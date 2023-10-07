package id.tisnahadiana.githubuserapi.core.data.source.remote.network

import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.api.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): Flow<List<User>>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): Flow<GithubDetailResponse>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Flow<List<User>>

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Flow<List<User>>

}