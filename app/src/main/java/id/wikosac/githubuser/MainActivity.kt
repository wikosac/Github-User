package id.wikosac.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.wikosac.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val mainViewModel by viewModels<MainViewModel>()
    private val handler = object : UserAdapter.ClickHandler {
        override fun onClick(position: Int, items: ItemsItem) {
//            if (actionMode != null) {
//                myAdapter.toggleSelection(position)
//                if (myAdapter.getSelection().isEmpty())
//                    actionMode?.finish()
//                else
//                    actionMode?.invalidate()
//                return
//            }
            val message = getString(R.string.klik, items.login)
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recycleView.addItemDecoration(itemDecoration)

        mainViewModel.listUsers.observe(this) { data ->
            setItemData(data)
        }
    }

    private fun setItemData(itemsItem: List<ItemsItem>) {
        val adapter = UserAdapter(itemsItem)
        binding.recycleView.adapter = adapter
    }
}