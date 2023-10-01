package id.tisnahadiana.githubuserapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase

class FavoriteViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {

    fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return githubUserUseCase.getFavoriteUser()
    }
}