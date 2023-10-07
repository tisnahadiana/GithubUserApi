package id.tisnahadiana.githubuserapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase

class FavoriteViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {
    suspend fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return githubUserUseCase.getFavoriteUser().asLiveData()
    }
}