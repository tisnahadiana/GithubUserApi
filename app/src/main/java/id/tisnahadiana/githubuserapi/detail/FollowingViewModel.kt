package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    val listFollowing = MutableLiveData<List<User>>()

    fun setListFollowing(username: String) {
        viewModelScope.launch {
            githubUserUseCase.getFollowing(username).collect { result ->
                if (result != null) {
                    listFollowing.postValue(result)
                } else {
                    Log.d("Failure", "Failed to fetch following.")
                }
            }
        }
    }

    fun getListFollowing(): LiveData<List<User>> {
        return listFollowing
    }
}