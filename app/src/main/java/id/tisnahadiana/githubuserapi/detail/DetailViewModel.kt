package id.tisnahadiana.githubuserapi.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.tisnahadiana.githubuserapi.core.data.source.remote.response.GithubDetailResponse
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.data.source.local.room.FavoriteUserDao
import id.tisnahadiana.githubuserapi.core.data.source.local.room.UserDatabase
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application, private val githubUserUseCase: GithubUserUseCase) :
    AndroidViewModel(application) {

    private val user = MutableLiveData<GithubDetailResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var userDao: FavoriteUserDao?
    private var userDB: UserDatabase?

    init {
        userDB = UserDatabase.getDatabase(application)
        userDao = userDB?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        _isLoading.value = true
        githubUserUseCase.getUserDetail(username).observeForever { result ->
            _isLoading.value = false
            if (result != null) {
                user.postValue(result)
            } else {
                Log.d("Failure", "Failed to fetch Detail Data.")
            }
        }
    }

    fun getUserDetail(): LiveData<GithubDetailResponse> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String, htmlUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(
                username,
                id,
                avatarUrl,
                htmlUrl
            )
            userDao?.addToFavorite(user)
        }
    }

    fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}