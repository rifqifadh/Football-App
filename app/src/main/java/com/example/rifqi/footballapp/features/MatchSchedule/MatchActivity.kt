package com.example.rifqi.footballapp.features.MatchSchedule

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.SearchMatch.SearchMatchActivity
import com.example.rifqi.footballapp.utils.Constants.Companion.LEAGUE_ID
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MatchActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: MatchPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val leagueId: String? = intent.getStringExtra(LEAGUE_ID)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Schedule"

        viewPagerAdapter = MatchPagerAdapter(this, supportFragmentManager, leagueId)

        viewPager = find(R.id.view_pager)
        viewPager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search -> {
                startActivity<SearchMatchActivity>()
                false
            }
            android.R.id.home -> {
                finish()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
