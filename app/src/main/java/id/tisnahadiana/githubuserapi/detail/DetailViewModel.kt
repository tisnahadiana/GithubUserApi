package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.domain.model.GithubUserDetail
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    private val user = MutableLiveData<GithubUserDetail>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            githubUserUseCase.getUserDetail(username).collect { apiResponse ->
                _isLoading.value = false
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val githubDetailResponse = apiResponse.data
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

    fun getUserDetail(): LiveData<GithubUserDetail> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        viewModelScope.launch {
            githubUserUseCase.addToFavorite(username, id, avatarUrl, htmlUrl)
        }
    }

    fun checkUser(id: Int) : Int = githubUserUseCase.checkUser(id)

    fun removeFromFavorite(id: Int) {
        viewModelScope.launch {
            githubUserUseCase.removeFromFavorite(id)
        }
    }
}