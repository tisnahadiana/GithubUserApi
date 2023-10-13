package id.tisnahadiana.githubuserapi.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.tisnahadiana.githubuserapi.R
import id.tisnahadiana.githubuserapi.detail.DetailUserActivity.Companion.EXTRA_USERNAME

class SectionPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val arguments: Bundle?
) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val TAB_1 = R.string.tab_1
        private const val TAB_2 = R.string.tab_2
        internal val TAB_TITLES = intArrayOf(TAB_1, TAB_2)
    }

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, arguments?.getString(EXTRA_USERNAME))

        return when (position) {
            0 -> FollowersFragment().apply {
                arguments = bundle
            }
            1 -> FollowingFragment().apply {
                arguments = bundle
            }
            else -> throw IllegalStateException("Invalid position $position")
        }
    }
}