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