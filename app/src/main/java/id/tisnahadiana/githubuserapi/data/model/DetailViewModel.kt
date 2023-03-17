package id.tisnahadiana.githubuserapi.data.model

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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String) {
        _isLoading.value = true
        apiService.getUserDetail(username).enqueue(object : Callback<GithubDetailResponse> {
            override fun onResponse(
                call: Call<GithubDetailResponse>,
                response: Response<GithubDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    user.value = response.body()
                } else {
                    Log.e("DetailViewModel", "getUserDetail onResponse error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<GithubDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("DetailViewModel", "getUserDetail onFailure error: ${t.message}")
            }
        })
    }

    fun getUserDetail(): LiveData<GithubDetailResponse> {
        return user
    }
}