package id.wikosac.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _users = MutableLiveData<ItemsItem>()
    private val _listItems = MutableLiveData<List<ItemsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val users: LiveData<ItemsItem> = _users
    val listUsers: LiveData<List<ItemsItem>> = _listItems
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val USERNAME = "Wiko"
    }

    init {
        findUser("Arif")
    }

    fun findUser(key: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(key)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listItems.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}