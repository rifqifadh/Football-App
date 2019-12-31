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
import com.example.rifqi.footballapp.features.Standings.StandingsActivity
import com.example.rifqi.footballapp.features.Teams.TeamsActivity
import com.example.rifqi.footballapp.model.Categories
import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.LeagueItem
import com.example.rifqi.footballapp.utils.Constants.Companion.LEAGUE_ID
import com.example.rifqi.footballapp.utils.Constants.Companion.SCHEDULE
import com.example.rifqi.footballapp.utils.Constants.Companion.STANDING
import com.example.rifqi.footballapp.utils.Constants.Companion.TEAM
import com.example.rifqi.footballapp.utils.EspressoIdlingResource
import com.example.rifqi.footballapp.utils.Functions.replaceCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailLeagueActivity : AppCompatActivity(), DetailLeagueImpl.View {

    private lateinit var presenter: DetailLeaguePresenter
    private lateinit var adapterCategories: CategoriesAdapter
    private var categories: MutableList<Categories> = mutableListOf()
    private var leagueId: String? = null
    private var twitter: String? = null
    private var facebook: String? = null
    private var leagues: LeagueItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        val leagueItem = intent.getParcelableExtra<LeagueItem>(LEAGUE_ID)
        leagueId = leagueItem.id
        leagues = leagueItem
        EspressoIdlingResource.increment()
        presenter = DetailLeaguePresenter(this)
        leagueId?.let { presenter.getDataLeague(it) }
        replaceCharacter(leagueItem.name.toString())?.let { presenter.getDataLeague(it) }

        setSupportActionBar(toolbar_league)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = leagueItem.name

        initDataCategory()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapterCategories = CategoriesAdapter(categories) {
            when (it.category) {
                SCHEDULE -> {
                    startActivity<MatchActivity>(LEAGUE_ID to leagues)
                }
                TEAM -> {
                    startActivity<TeamsActivity>(LEAGUE_ID to leagues)
                }
                STANDING -> {
                    startActivity<StandingsActivity>(LEAGUE_ID to leagues)
                }
            }
        }
        rv_categories.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        rv_categories.adapter = adapterCategories
    }

    override fun setData(data: List<DetailLeagueItem>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        data?.map {
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

    private fun initDataCategory() {
        val category = resources.getStringArray(R.array.categories)
        val image = resources.obtainTypedArray(R.array.categories_icon)
        categories.clear()
        for (i in category.indices) {
            categories.add(
                Categories(
                    category[i],
                    image.getResourceId(i, 0)
                )
            )
        }
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
