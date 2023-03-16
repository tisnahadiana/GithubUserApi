package id.tisnahadiana.githubuserapi.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.tisnahadiana.githubuserapi.R

class SectionPagerAdapter(private val Ctx: Context, fm: FragmentManager, data: Bundle) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val TAB_1 = R.string.tab_1
        private const val TAB_2 = R.string.tab_2
        private val TAB_TITLES = intArrayOf(TAB_1, TAB_2)
    }

    private val fragmentBundle: Bundle = data

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FollowersFragment()
            1 -> FollowingFragment()
            else -> throw IllegalStateException("Invalid position $position")
        }.apply {
            arguments = fragmentBundle
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Ctx.resources.getString(TAB_TITLES[position])
    }

}