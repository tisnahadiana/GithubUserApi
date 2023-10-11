package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.data.source.remote.network.ApiResponse
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    private val user = MutableLiveData<GithubDetailResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            githubUserUseCase.getUserDetail(username).collect { apiResponse ->
                _isLoading.value = false
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val githubDetailResponse = apiResponse.data // Extract GithubDetailResponse from ApiResponse.Success
                        user.postValue(githubDetailResponse)
                    }
                    is ApiResponse.Error -> {
                        Log.e("DetailViewModel", "Error occurred: ${apiResponse.errorMessage}")
                    }
                    ApiResponse.Empty -> {
                        Log.w("DetailViewModel", "Empty response received.")
                    }
                }
            }
        }
    }

    fun getUserDetail(): LiveData<GithubDetailResponse> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        viewModelScope.launch {
            githubUserUseCase.addToFavorite(username, id, avatarUrl, htmlUrl)
        }
    }

    suspend fun checkUser(id: Int) : Int = githubUserUseCase.checkUser(id)

    fun removeFromFavorite(id: Int) {
        viewModelScope.launch {
            githubUserUseCase.removeFromFavorite(id)
        }
    }
}