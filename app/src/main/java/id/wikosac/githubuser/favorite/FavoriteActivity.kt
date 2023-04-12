package id.wikosac.githubuser.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.wikosac.githubuser.database.Favorite
import id.wikosac.githubuser.databinding.ActivityFavoriteBinding
import id.wikosac.githubuser.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

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
            val adapter = FavoriteAdapter(items)
            binding.recycleViewFav.adapter = adapter
            adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Favorite) {
                    showSelected(data.id, data.login, data.avatarUrl)
                }
            })
        }
    }

    private fun showSelected(id: Int?, nickname: String?, avatarUrl: String?) {
        val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_ITEM, nickname)
        intent.putExtra(DetailActivity.EXTRA_AVA, avatarUrl)
        startActivity(intent)
    }
}