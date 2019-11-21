package com.example.rifqi.footballapp.features.MatchSchedule

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.NextMatch.NextMatchFragment
import com.example.rifqi.footballapp.features.MatchSchedule.PrevMatch.PrevMatchFragment

class MatchPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    leagueId: String?
) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList = listOf(
        leagueId?.let { PrevMatchFragment().newInstance(it) },
        leagueId?.let { NextMatchFragment().newInstance(it) }
    )

    override fun getItem(position: Int): Fragment {
        return fragmentList[position] as Fragment
    }

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): String? =
        context.resources.getStringArray(R.array.fragment_match)[position]

}