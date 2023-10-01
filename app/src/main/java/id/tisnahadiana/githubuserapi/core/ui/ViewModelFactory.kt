package id.tisnahadiana.githubuserapi.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.tisnahadiana.githubuserapi.core.di.Injection
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.detail.DetailViewModel
import id.tisnahadiana.githubuserapi.favorite.FavoriteViewModel

class ViewModelFactory private constructor(private val githubUserUseCase: GithubUserUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideGithubUserUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(githubUserUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(githubUserUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}