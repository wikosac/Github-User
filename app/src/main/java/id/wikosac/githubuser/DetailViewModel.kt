package id.wikosac.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _users = MutableLiveData<DetailResponse>()
    private val _listUsers = MutableLiveData<List<DetailResponse>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val users: LiveData<DetailResponse> = _users
    val listUsers: LiveData<List<DetailResponse>> = _listUsers
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun showUser(key: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(key)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _users.value = response.body()
                } else {
                    Log.e(TAG, "onFailuree: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}