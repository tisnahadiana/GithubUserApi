package id.tisnahadiana.githubuserapi.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.tisnahadiana.githubuserapi.core.api.SearchResponse
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiService
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.repository.IGithubUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) :
    IGithubUserRepository {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    override fun getFollowers(username: String): LiveData<List<User>?> {
        val resultLiveData = MutableLiveData<List<User>?>()
        apiService.getFollowers(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    resultLiveData.postValue(response.body())
                } else {
                    resultLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                resultLiveData.postValue(null)
            }
        })
        return resultLiveData
    }

    override fun getFollowing(username: String): LiveData<List<User>?> {
        val resultLiveData = MutableLiveData<List<User>?>()
        apiService.getFollowing(username).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    resultLiveData.postValue(response.body())
                } else {
                    resultLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                resultLiveData.postValue(null)
            }
        })
        return resultLiveData
    }

    override fun getUserDetail(username: String): LiveData<GithubDetailResponse?> {
        val resultLiveData = MutableLiveData<GithubDetailResponse?>()
        apiService.getUserDetail(username).enqueue(object : Callback<GithubDetailResponse> {
            override fun onResponse(
                call: Call<GithubDetailResponse>,
                response: Response<GithubDetailResponse>
            ) {
                if (response.isSuccessful) {
                    resultLiveData.postValue(response.body())
                } else {
                    resultLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<GithubDetailResponse>, t: Throwable) {
                resultLiveData.postValue(null)
            }
        })
        return resultLiveData
    }

    override fun getSearchUsers(query: String): LiveData<List<User>?> {
        val resultLiveData = MutableLiveData<List<User>?>()
        apiService.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()?.items ?: emptyList()
                    resultLiveData.postValue(users)
                } else {
                    resultLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                resultLiveData.postValue(null)
            }
        })
        return resultLiveData
    }
}