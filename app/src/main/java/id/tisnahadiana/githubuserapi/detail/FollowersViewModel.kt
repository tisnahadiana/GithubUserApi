package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.domain.model.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    val listFollowers = MutableLiveData<List<User>>()
    fun setListFollowers(username: String) {
        viewModelScope.launch {
            githubUserUseCase.getFollowers(username).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val followers = apiResponse.data
                        listFollowers.postValue(followers)
                    }
                    is ApiResponse.Error -> {
                        Log.e("FollowersViewModel", "Error occurred: ${apiResponse.errorMessage}")
                    }
                    ApiResponse.Empty -> {
                        Log.w("FollowersViewModel", "Empty response received.")
                    }
                }
            }
        }
    }

    fun getListFollowers(): LiveData<List<User>> {
        return listFollowers
    }
}