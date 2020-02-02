package com.delimce.cabifymobilechallenge.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.delimce.cabifymobilechallenge.R
import com.delimce.cabifymobilechallenge.ui.fragments.OrderFragment
import com.delimce.cabifymobilechallenge.ui.fragments.PlaceholderFragment
import com.delimce.cabifymobilechallenge.ui.fragments.ProductFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        println(position)

        return when (position) {
            0 -> ProductFragment()
            1 -> OrderFragment()
            else -> PlaceholderFragment.newInstance(2)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}