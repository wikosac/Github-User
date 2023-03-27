package id.wikosac.githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.wikosac.githubuser.R
import id.wikosac.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_ITEM = "extra_item"
        const val TAG = "detail"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.foll,
            R.string.fill
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nickname = intent.getStringExtra(EXTRA_ITEM).toString()
        detailViewModel.showUser(nickname).toString()
        detailViewModel.users.observe(this) {
            with (binding) {
                Glide.with(avatar).load(it.avatarUrl)
                    .error(R.drawable.ic_baseline_broken_image_24).into(avatar)
                username.text = if (it.name != null) it.name else it.login
                bio.text = if (it.bio != null) it.bio else "-"
                val foll = String.format(resources.getString(R.string.follower, it.followers.toString()))
                val fill = String.format(resources.getString(R.string.following, it.following.toString()))
                follower.text = foll
                following.text = fill
            }
        }

        val sectionsPagerAdapter = FollowAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}