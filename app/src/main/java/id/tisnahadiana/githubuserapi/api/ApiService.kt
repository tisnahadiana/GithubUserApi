package id.tisnahadiana.githubuserapi.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(
        @Query("q") query: String
    ) : Call<SearchResponse>
}