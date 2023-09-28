package id.tisnahadiana.githubuserapi.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase

class FollowingViewModel (private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    val listFollowing = MutableLiveData<List<User>>()

    fun setListFollowing(username: String) {
        githubUserUseCase.getFollowing(username).observeForever { result ->
            if (result != null) {
                listFollowing.postValue(result)
            } else {
                Log.d("Failure", "Failed to fetch following.")
            }
        }
    }

    fun getListFollowing(): LiveData<List<User>> {
        return listFollowing
    }
}