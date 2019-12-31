package com.example.rifqi.footballapp.features.Teams.Search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Teams.TeamAdapter
import com.example.rifqi.footballapp.features.Teams.model.Team
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamImpl.View {

    private var teamsItem: MutableList<Team.TeamItem> = mutableListOf()
    private val presenter by lazy { SearchTeamPresenter(this) }
    private val adapter by lazy { TeamAdapter(teamsItem, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        rv_search_teams.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_search_teams.adapter = adapter
    }

    override fun setSearchTeams(data: List<Team.TeamItem>) {
        teamsItem.clear()
        teamsItem.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getSearchTeams(newText?.replace(" ", "_").toString())
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchTeams(query?.replace(" ", "_").toString())
                return true
            }
        })
        menu.findItem(R.id.action_search).expandActionView()

        return true
    }
}
