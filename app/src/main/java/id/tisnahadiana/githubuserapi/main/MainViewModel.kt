package id.tisnahadiana.githubuserapi.main

import androidx.lifecycle.*
import id.tisnahadiana.githubuserapi.core.api.User
import id.tisnahadiana.githubuserapi.core.data.source.local.SettingPreferences
import id.tisnahadiana.githubuserapi.core.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferences: SettingPreferences,
    private val githubUserUseCase: GithubUserUseCase
) : ViewModel() {

    private val listUsers = MutableLiveData<List<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        viewModelScope.launch {
            githubUserUseCase.getSearchUsers(query).collect { users ->
                listUsers.postValue(users)
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
