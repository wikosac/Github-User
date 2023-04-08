package id.wikosac.githubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE favorite.id = :id")
    fun delete(id: Int)

    @Query("SELECT * from favorite ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Favorite>>
}