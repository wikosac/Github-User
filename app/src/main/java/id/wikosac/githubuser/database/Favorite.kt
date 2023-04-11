package id.wikosac.githubuser.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

) : Parcelable
