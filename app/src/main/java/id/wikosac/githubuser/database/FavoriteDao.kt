package id.wikosac.githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: FavoriteUser)
    @Update
    fun update(favoriteUser: FavoriteUser)
    @Delete
    fun delete(favoriteUser: FavoriteUser)
    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<FavoriteUser>>
}