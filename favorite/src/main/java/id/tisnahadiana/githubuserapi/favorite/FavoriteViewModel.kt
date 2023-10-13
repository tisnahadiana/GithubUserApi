package id.tisnahadiana.githubuserapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.source.local.entity.FavoriteUser
class FavoriteViewModel (private val githubUserUseCase: GithubUserUseCase) :
    ViewModel() {
    fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return githubUserUseCase.getFavoriteUser().asLiveData()
    }
}