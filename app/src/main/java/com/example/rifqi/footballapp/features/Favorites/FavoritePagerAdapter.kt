package com.example.rifqi.footballapp.features.Favorites

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Favorites.FavoriteMatch.FavoritesMatchFragment
import com.example.rifqi.footballapp.features.Favorites.FavoriteTeam.FavoritesTeamFragment

class FavoritePagerAdapter(
    private val context: Context?,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager){

    private val fragmentList = listOf(
        FavoritesMatchFragment(),
        FavoritesTeamFragment()
    )

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): String? =
        context?.resources!!.getStringArray(R.array.title_fav_pager)[position]
}