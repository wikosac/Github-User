package id.wikosac.githubuser.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var appName: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowFragment.ARG_SECTION_NUMBER, position + 1)
            putString(FollowFragment.ARG_NAME, appName)
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}