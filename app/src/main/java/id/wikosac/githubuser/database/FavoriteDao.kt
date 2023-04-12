package id.wikosac.githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Query("DELETE FROM Favorite WHERE favorite.id = :id")
    fun delete(id: Int)

    @Query("SELECT * from Favorite ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<Favorite>>

    @Query("SELECT count(*) FROM Favorite WHERE login = :login")
    fun getFavoriteUserByUsername(login: String): Int
}