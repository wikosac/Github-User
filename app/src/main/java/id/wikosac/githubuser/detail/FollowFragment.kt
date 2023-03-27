package id.wikosac.githubuser.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.wikosac.githubuser.databinding.FragmentFollowBinding
import id.wikosac.githubuser.main.ItemsItem
import id.wikosac.githubuser.main.MainAdapter


class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var detailViewModel: DetailViewModel
    private var position: Int = 0
    private var username: String? = null

    companion object {
        const val ARG_POSITION = "section_number"
        const val ARG_USERNAME = "app_name"
        const val TAG = "followfragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        detailViewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        binding.recycleView.layoutManager = LinearLayoutManager(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        detailViewModel.showFollowers(username.toString())
        detailViewModel.showFollowing(username.toString())

        if (position == 1){
            detailViewModel.listFollowers.observe(viewLifecycleOwner) {
                setItemData(it)
            }
        } else {
            detailViewModel.listFollowing.observe(viewLifecycleOwner) {
                setItemData(it)
            }
        }
    }

    private fun setItemData(itemsItem: List<ItemsItem>) {
        val adapter = DetailAdapter(itemsItem)
        binding.recycleView.adapter = adapter
    }
}