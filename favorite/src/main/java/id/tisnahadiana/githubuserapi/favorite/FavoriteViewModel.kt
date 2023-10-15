package id.tisnahadiana.githubuserapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.tisnahadiana.githubuserapi.core.domain.model.Favorite
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
class FavoriteViewModel (private val githubUserUseCase: GithubUserUseCase) :
    ViewModel() {
    fun getFavoriteUser(): LiveData<List<Favorite>> {
        return githubUserUseCase.getFavoriteUser().asLiveData()
    }
}