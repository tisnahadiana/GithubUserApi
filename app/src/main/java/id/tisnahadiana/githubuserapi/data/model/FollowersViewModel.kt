package id.tisnahadiana.githubuserapi.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.tisnahadiana.githubuserapi.api.ApiConfig
import id.tisnahadiana.githubuserapi.api.ApiService
import id.tisnahadiana.githubuserapi.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val apiService: ApiService = ApiConfig.getApiService()
    val listFollowers = MutableLiveData<ArrayList<User>>()
    val errorMessage = MutableLiveData<String>()

    fun setListFollowers(username: String) {
        apiService.getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    } else {
                        errorMessage.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                    errorMessage.postValue(t.message)
                }

            })
    }

    fun getListFollowers(): LiveData<ArrayList<User>> {
        return listFollowers
    }

    fun getErrorMessage(): LiveData<String> {
        return errorMessage
    }
}