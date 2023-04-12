package id.wikosac.githubuser.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.wikosac.githubuser.database.Favorite
import id.wikosac.githubuser.database.FavoriteDao
import id.wikosac.githubuser.database.FavoriteDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private var favoriteDatabase: FavoriteDatabase
    private var favoriteDao: FavoriteDao?

    init{
        favoriteDatabase = FavoriteDatabase.getDatabase(application)
        favoriteDao = favoriteDatabase.favoriteDao()
    }

    fun getAllFavUser() : LiveData<List<Favorite>>? = favoriteDao?.getAllFavorite()
}