package com.example.rifqi.footballapp.features.DetailLeague

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.MatchActivity
import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.LeagueItem
import com.example.rifqi.footballapp.model.Team
import com.example.rifqi.footballapp.utils.Constants.Companion.LEAGUE_ID
import com.example.rifqi.footballapp.utils.Functions.replaceCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailLeagueActivity : AppCompatActivity(), DetailLeagueImpl.View {

    private lateinit var presenter: DetailLeaguePresenter
    private var teams: MutableList<Team.TeamItem> = mutableListOf()
    private var leagueId: String? = null
    private var twitter: String? = null
    private var facebook: String? = null
    private val adapter by lazy { TeamAdapter(teams, applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        val leagues = intent.getParcelableExtra<LeagueItem>(LEAGUE_ID)
        leagueId = leagues.id
        presenter = DetailLeaguePresenter(this)
        leagueId?.let { presenter.getDataLeague(it) }
        presenter.getListTeams(leagues.name?.let { replaceCharacter(it) } ?: "")
        replaceCharacter(leagues.name.toString())?.let { presenter.getDataLeague(it) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = leagues.name

        rv_teams.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        rv_teams.adapter = adapter

        ib_check_schedule.setOnClickListener {
            startActivity<MatchActivity>(LEAGUE_ID to leagueId)
        }
    }

    override fun setData(data: List<DetailLeagueItem>?) {
        data?.map {
            tv_league.text = it.strLeague
            tv_country.text = it.strCountry
            tv_formed_year.text = it.intFormedYear.toString()
            tv_website.text = it.strWebsite
            tv_desc?.text = it.strDescription
            twitter = it.strTwitter
            facebook = it.strFacebook

            it.strLogo?.let { it1 -> Picasso.get().load(it1).into(iv_logo) }

            ib_twitter.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.${twitter}"))
                startActivity(browserIntent)
            }
            ib_facebook.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.${facebook}"))
                startActivity(browserIntent)
            }
        }
    }


    override fun setDataTeams(data: List<Team.TeamItem>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        container_detail.visibility = View.GONE
        animation_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        animation_loading.visibility = View.GONE
        container_detail.visibility = View.VISIBLE
    }

    override fun showErrorMessage(message: String?) {
        toast(message.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
