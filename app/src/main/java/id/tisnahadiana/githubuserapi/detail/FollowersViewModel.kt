package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.launch

class FollowersViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    val listFollowers = MutableLiveData<List<User>>()
    fun setListFollowers(username: String) {
        viewModelScope.launch {
            githubUserUseCase.getFollowers(username).collect { result ->
                if (result != null) {
                    listFollowers.postValue(result)
                } else {
                    Log.d("Failure", "Failed to fetch Followers.")
                }
            }
        }
    }

    fun getListFollowers(): LiveData<List<User>> {
        return listFollowers
    }
}