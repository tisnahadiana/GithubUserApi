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