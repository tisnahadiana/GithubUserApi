package id.tisnahadiana.githubuserapi.core.data.source.remote

import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiService
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getFollowers(username: String, callback: (List<User>?, String?) -> Unit) {
        apiService.getFollowers(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, response.message())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

    fun getFollowing(username: String, callback: (List<User>?, String?) -> Unit) {
        apiService.getFollowing(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, response.message())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

    fun getUserDetail(username: String, callback: (GithubDetailResponse?, String?) -> Unit) {
        apiService.getUserDetail(username).enqueue(object : Callback<GithubDetailResponse> {
            override fun onResponse(call: Call<GithubDetailResponse>, response: Response<GithubDetailResponse>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, response.message())
                }
            }

            override fun onFailure(call: Call<GithubDetailResponse>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

    fun getSearchUsers(query: String, callback: (List<User>?, String?) -> Unit) {
        apiService.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.items ?: emptyList()
                    callback(users, null)
                } else {
                    callback(null, response.message())
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }
}