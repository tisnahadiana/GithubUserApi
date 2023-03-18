package id.tisnahadiana.githubuserapi.data.model

import android.util.Log
import androidx.lifecycle.*
import id.tisnahadiana.githubuserapi.api.ApiConfig
import id.tisnahadiana.githubuserapi.api.ApiService
import id.tisnahadiana.githubuserapi.api.SearchResponse
import id.tisnahadiana.githubuserapi.api.User
import id.tisnahadiana.githubuserapi.data.local.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val preferences: SettingPreferences) : ViewModel() {

    private val apiService: ApiService = ApiConfig.getApiService()
    private val listUsers = MutableLiveData<ArrayList<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        apiService.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()?.items ?: arrayListOf()
                    listUsers.postValue(users)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("Failure", t.message ?: "Unknown error")
            }
        })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUsers
    }

    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}
