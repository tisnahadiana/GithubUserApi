package id.tisnahadiana.githubuserapi.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.tisnahadiana.githubuserapi.api.ApiConfig
import id.tisnahadiana.githubuserapi.api.ApiService
import id.tisnahadiana.githubuserapi.api.SearchResponse
import id.tisnahadiana.githubuserapi.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val apiService: ApiService = ApiConfig.getApiService()
    private val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        apiService.searchUsers(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
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
}
