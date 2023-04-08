package id.wikosac.githubuser.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.wikosac.githubuser.ApiConfig
import id.wikosac.githubuser.database.Favorite
import id.wikosac.githubuser.database.FavoriteDao
import id.wikosac.githubuser.database.FavoriteDatabase
import id.wikosac.githubuser.main.ItemsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private var favoriteDatabase: FavoriteDatabase
    private var favoriteDao: FavoriteDao?

    init{
        favoriteDatabase = FavoriteDatabase.getDatabase(application)
        favoriteDao = favoriteDatabase.favoriteDao()
    }
    private val _users = MutableLiveData<DetailResponse>()
    private val _listFollowers = MutableLiveData<List<ItemsItem>>()
    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val users: LiveData<DetailResponse> = _users
    val listFollowers: LiveData<List<ItemsItem>> = _listFollowers
    val listFollowing: LiveData<List<ItemsItem>> = _listFollowing
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

    fun showFollowers(key: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(key)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowers.value = response.body()
                } else {
                    Log.e(TAG, "onFailuree: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun showFollowing(key: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(key)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailuree: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun favUser(id: Int, username: String, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = Favorite(id, username, avatarUrl)
            favoriteDao?.insert(user)
        }
    }
    fun unfavUser( id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            favoriteDao?.delete(id)
        }
    }
}