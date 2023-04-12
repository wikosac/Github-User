package id.wikosac.githubuser.detail

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.wikosac.githubuser.R
import id.wikosac.githubuser.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_ITEM = "extra_item"
        const val EXTRA_AVA = "extra_ava"
        const val EXTRA_ID = "extra_id"
        const val TAG = "detail"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.foll,
            R.string.fill
        )
        var nickname = ""
        var avatarUrl = ""
        var id = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra(EXTRA_ID, 0)
        nickname = intent.getStringExtra(EXTRA_ITEM).toString()
        avatarUrl = intent.getStringExtra(EXTRA_AVA).toString()

        detailViewModel.showUser(nickname).toString()
        detailViewModel.users.observe(this) {
            with (binding) {
                Glide.with(avatarView).load(it.avatarUrl)
                    .error(R.drawable.ic_baseline_broken_image_24).into(avatarView)
                name.text = if (it.name != null) it.name else "-"
                usernameView.text = it.login
                bio.text = if (it.bio != null) it.bio else "-"
                val foll = String.format(resources.getString(R.string.follower, it.followers.toString()))
                val fill = String.format(resources.getString(R.string.following, it.following.toString()))
                follower.text = foll
                following.text = fill
            }
        }
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        //follower following
        val sectionsPagerAdapter = FollowAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        var clicked = false
        CoroutineScope(Dispatchers.IO).launch {
            val checkUser = detailViewModel.checkUser(nickname)
            withContext(Dispatchers.Main){
                if (checkUser != null){
                    clicked = checkUser > 0
                    binding.fab.setImageResource(
                        if (clicked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                    )
                }
            }
        }

        binding.fab.setOnClickListener {
            clicked = !clicked
            if (clicked){
                detailViewModel.favUser(id, nickname, avatarUrl)
                binding.fab.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(this, "Disimpan ke favorit", Toast.LENGTH_SHORT).show()
            } else {
                detailViewModel.unFavUser(id)
                binding.fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Toast.makeText(this, "Daftar favorit diperbarui", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}