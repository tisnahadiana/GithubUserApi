package id.tisnahadiana.githubuserapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.data.source.local.entity.FavoriteUser
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val githubUserUseCase: GithubUserUseCase) :
    ViewModel() {
    fun getFavoriteUser(): LiveData<List<FavoriteUser>> {
        return githubUserUseCase.getFavoriteUser().asLiveData()
    }
}