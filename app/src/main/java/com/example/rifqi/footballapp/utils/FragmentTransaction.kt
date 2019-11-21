package com.example.rifqi.footballapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.rifqi.footballapp.R

open class FragmentTransaction {

    companion object {
        fun pushFragments(activity: FragmentActivity?, tag: String, fragment: Fragment) {

            val manager = activity!!.supportFragmentManager
            val ft = manager.beginTransaction()

            if (manager.findFragmentByTag(tag) == null) {
                ft.add(R.id.main_container, fragment, tag)
            }

            val fragmentLeagues = manager.findFragmentByTag(Constants.TAG_LEAGUES)
            val fragmentFavorites = manager.findFragmentByTag(Constants.TAG_FAVORITES)

            if (fragmentLeagues != null) {
                ft.hide(fragmentLeagues)
            }

            if (fragmentFavorites != null) {
                ft.hide(fragmentFavorites)
            }

            when(tag) {
                Constants.TAG_LEAGUES -> {
                    if (fragmentLeagues != null) {
                        ft.show(fragmentLeagues)
                    }
                }
                Constants.TAG_FAVORITES -> {
                    if (fragmentFavorites != null) {
                        ft.show(fragmentFavorites)
                    }
                }
            }

            ft.commitAllowingStateLoss()
        }
    }
}