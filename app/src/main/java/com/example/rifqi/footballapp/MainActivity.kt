package com.example.rifqi.footballapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rifqi.footballapp.features.Favorites.FavoriteFragment
import com.example.rifqi.footballapp.features.Leagues.LeaguesFragment
import com.example.rifqi.footballapp.utils.Constants.Companion.TAG_FAVORITES
import com.example.rifqi.footballapp.utils.Constants.Companion.TAG_LEAGUES
import com.example.rifqi.footballapp.utils.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentTransaction.pushFragments(this, TAG_LEAGUES, LeaguesFragment())
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                bottom_navigation.selectedItemId -> return@setOnNavigationItemSelectedListener false
                R.id.leagues -> {
                    FragmentTransaction.pushFragments(this, TAG_LEAGUES, LeaguesFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorites -> {
                    FragmentTransaction.pushFragments(this, TAG_FAVORITES,
                        FavoriteFragment()
                    )
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        bottom_navigation.selectedItemId = R.id.leagues
    }
}
