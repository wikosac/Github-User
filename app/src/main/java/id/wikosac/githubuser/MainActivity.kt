package id.wikosac.githubuser

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.wikosac.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recycleView.addItemDecoration(itemDecoration)

        mainViewModel.listUsers.observe(this) { user ->
            setUserData(user)
        }
    }

    private fun setUserData(users: List<ItemsItem>) {
        val listUser = ArrayList<String>()
        for (user in users) {
            listUser.add(user.login)
        }
        val adapter = UserAdapter(listUser)
        binding.recycleView.adapter = adapter
    }
}