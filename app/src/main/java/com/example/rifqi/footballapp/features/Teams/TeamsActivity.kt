package com.example.rifqi.footballapp.features.Teams

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Teams.Search.SearchTeamActivity
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.model.LeagueItem
import com.example.rifqi.footballapp.utils.Constants
import kotlinx.android.synthetic.main.activity_teams.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.startActivity

class TeamsActivity : AppCompatActivity(), TeamsImpl.View {

    private var teamsItem: MutableList<Team.TeamItem> = mutableListOf()
    private val adapter by lazy { TeamAdapter(teamsItem, this) }
    private val presenter by lazy { TeamsPresenter(this) }
    private var leagueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        val leagueItem = intent.getParcelableExtra<LeagueItem>(Constants.LEAGUE_ID)
        leagueId = leagueItem.id

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = leagueItem.name

        presenter.getListTeams(leagueId!!)
        rv_teams.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_teams.adapter = adapter
    }

    override fun setListTeams(data: List<Team.TeamItem>) {
        teamsItem.clear()
        teamsItem.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        animation_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        animation_loading.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_search -> {
                startActivity<SearchTeamActivity>()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
