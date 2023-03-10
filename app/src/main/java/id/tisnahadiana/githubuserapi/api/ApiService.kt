package id.tisnahadiana.githubuserapi.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users") fun searchUsers(@Query("q") query: String) : Call<SearchResponse>
    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String): Call<GithubDetailResponse>

}