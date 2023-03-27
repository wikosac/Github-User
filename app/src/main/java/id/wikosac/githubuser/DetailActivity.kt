package id.wikosac.githubuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import id.wikosac.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ITEM, ItemsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ITEM)
        }
        if (user != null) {
            with (binding) {
                Glide.with(avatar).load(user.avatarUrl).error(R.drawable.ic_baseline_broken_image_24).into(avatar)
                username.text = user.login
//                bio.text = user.bio
            }
        }
    }
}