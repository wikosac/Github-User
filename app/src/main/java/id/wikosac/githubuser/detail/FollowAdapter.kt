package id.wikosac.githubuser.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val nickname = DetailActivity.nickname

    companion object {
        const val TAG = "FollowAdapter"
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_POSITION, position + 1)
            putString(FollowFragment.ARG_USERNAME, nickname)
            Log.d(TAG, nickname)
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}