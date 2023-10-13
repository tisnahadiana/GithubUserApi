package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    val listFollowing = MutableLiveData<List<User>>()

    fun setListFollowing(username: String) {
        viewModelScope.launch {
            githubUserUseCase.getFollowing(username).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val users = apiResponse.data
                        listFollowing.postValue(users)
                    }
                    is ApiResponse.Error -> {
                        Log.e("FollowingViewModel", "Error occurred: ${apiResponse.errorMessage}")
                    }
                    ApiResponse.Empty -> {
                        Log.w("FollowingViewModel", "Empty response received.")
                    }
                }
            }
        }
    }

    fun getListFollowing(): LiveData<List<User>> {
        return listFollowing
    }
}