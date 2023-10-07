package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    private val user = MutableLiveData<GithubDetailResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String) {
        viewModelScope.launch {
            _isLoading.value = true
            githubUserUseCase.getUserDetail(username).collect { result ->
                _isLoading.value = false
                if (result != null) {
                    user.postValue(result)
                } else {
                    Log.d("Failure", "Failed to fetch Detail Data.")
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