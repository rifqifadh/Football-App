package com.example.rifqi.footballapp.features.Standings

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Standings.model.Standing
import com.example.rifqi.footballapp.model.LeagueItem
import com.example.rifqi.footballapp.utils.Constants.Companion.LEAGUE_ID
import kotlinx.android.synthetic.main.activity_standings.*
import kotlinx.android.synthetic.main.loading.*

class StandingsActivity : AppCompatActivity(), StandingsImpl.View {

    private var standingsItems: MutableList<Standing.Standings> = mutableListOf()
    private val adapter by lazy { StandingsAdapter(standingsItems, this) }
    private val presenter by lazy { StandingsPresenter(this) }
    private var leagueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standings)

        val leagueItem = intent.getParcelableExtra<LeagueItem>(LEAGUE_ID)
        leagueId = leagueItem.id

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = leagueItem.name

        presenter.getDataStandings(leagueId!!, "1920")
        rv_standings.layoutManager = LinearLayoutManager(this)
        rv_standings.adapter = adapter
    }


    override fun setDataStandings(data: List<Standing.Standings>) {
        standingsItems.clear()
        standingsItems.addAll(data)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
