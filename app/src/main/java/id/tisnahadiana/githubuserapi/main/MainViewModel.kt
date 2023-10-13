package id.tisnahadiana.githubuserapi.main

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import id.tisnahadiana.githubuserapi.core.source.local.SettingPreferences
import id.tisnahadiana.githubuserapi.core.source.remote.network.ApiResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: SettingPreferences,
    private val githubUserUseCase: GithubUserUseCase
) : ViewModel() {

    private val listUsers = MutableLiveData<List<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        viewModelScope.launch {
            githubUserUseCase.getSearchUsers(query).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val searchResponse = apiResponse.data
                        val users = searchResponse.items ?: emptyList()
                        listUsers.postValue(users)
                    }
                    is ApiResponse.Error -> {
                        Log.e("MainViewModel", "Error occurred: ${apiResponse.errorMessage}")
                    }
                    ApiResponse.Empty -> {
                        Log.w("MainViewModel", "Empty response received.")
                    }
                }
            }
        }
    }

    fun getSearchUsers(): LiveData<List<User>> {
        return listUsers
    }

    class Factory(
        private val preferences: SettingPreferences,
        private val githubUserUseCase: GithubUserUseCase
    ) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences, githubUserUseCase) as T
    }
}
