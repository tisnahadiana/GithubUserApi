package id.tisnahadiana.githubuserapi.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.tisnahadiana.githubuserapi.api.ApiConfig
import id.tisnahadiana.githubuserapi.api.ApiService
import id.tisnahadiana.githubuserapi.api.GithubDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val apiService: ApiService = ApiConfig.getApiService()
    private val user = MutableLiveData<GithubDetailResponse>()

    fun setUserDetail(username: String) {
        apiService.getUserDetail(username).enqueue(object : Callback<GithubDetailResponse> {
            override fun onResponse(call: Call<GithubDetailResponse>, response: Response<GithubDetailResponse>) {
                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    Log.e("DetailViewModel", "getUserDetail onResponse error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GithubDetailResponse>, t: Throwable) {
                Log.e("DetailViewModel", "getUserDetail onFailure error: ${t.message}")
            }
        })
    }

    fun getUserDetail(): LiveData<GithubDetailResponse> {
        return user
    }
}