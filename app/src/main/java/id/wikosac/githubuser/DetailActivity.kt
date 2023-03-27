package id.wikosac.githubuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import id.wikosac.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_ITEM = "extra_item"
        const val TAG = "detail"
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
                username.text = it.name
                bio.text = it.bio
                val foll = String.format(resources.getString(R.string.follower, it.followers.toString()))
                val fill = String.format(resources.getString(R.string.following, it.following.toString()))
                follower.text = foll
                following.text = fill
            }
        }
    }
}