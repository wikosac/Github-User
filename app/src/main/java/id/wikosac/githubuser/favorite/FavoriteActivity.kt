package id.wikosac.githubuser.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.wikosac.githubuser.database.Favorite
import id.wikosac.githubuser.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    companion object {
        const val TAG = "favac"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleViewFav.layoutManager = LinearLayoutManager(this)
        binding.recycleViewFav.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
        favoriteViewModel.getAllFavUser()?.observe(this) { users: List<Favorite> ->
            val items = arrayListOf<Favorite>()
            users.map {
                val item = Favorite(login = it.login, avatarUrl = it.avatarUrl, id = it.id)
                items.add(item)
            }
            binding.recycleViewFav.adapter = FavoriteAdapter(items)
            Log.d(TAG, "onCreate: $items")
        }
    }
}