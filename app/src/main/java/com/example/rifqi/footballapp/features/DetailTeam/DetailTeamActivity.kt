package com.example.rifqi.footballapp.features.DetailTeam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.DetailTeam.model.DetailTeam
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.helper.database
import com.example.rifqi.footballapp.utils.Constants.Companion.TEAM
import com.example.rifqi.footballapp.utils.Functions.snackBar
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), DetailTeamImpl.View {

    private val presenter by lazy { DetailTeamPresenter(this) }
    private var teamsItem: Team.TeamItem? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setSupportActionBar(toolbar_detail_team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initData()
        fab_favorite.setOnClickListener {
            if (isFavorite) removeFromFavorite() else addToFavorite()

            isFavorite = !isFavorite
            setFavorite()
        }
    }

    fun initData() {
        val teamItem = intent.getParcelableExtra<Team.TeamItem>(TEAM)
        teamsItem = teamItem

        supportActionBar?.title = teamItem.strLeague
        favoriteState()
        setFavorite()

        teamItem.idTeam?.let { presenter.getDetailTeam(it) }

        Picasso.get().load(teamItem.strStadiumThumb).transform(BlurTransformation(this, 25, 1))
            .into(background_team)
        Picasso.get().load(teamItem.strTeamBadge).into(img_team_logo)
        tv_team_name.text = teamItem.strTeam
    }


    override fun setDetailTeam(data: List<DetailTeam.DetailTeam>?) {
        data?.map {
            tv_team_stadium.text = it.strStadium
            tv_detail_team_website.text = it.strWebsite
            tv_detail_team_facebook.text = it.strFacebook
            tv_detail_team_twitter.text = it.strTwitter
            tv_detail_instagram.text = it.strInstagram
            tv_detail_youtube.text = it.strYoutube
            tv_team_desc.text = it.strDescriptionEN
            tv_stadium_desc.text = it.strStadiumDescription
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Team.TeamItem.TABLE_FAVORITE_TEAM,
                    Team.TeamItem.TEAM_ID to teamsItem?.idTeam,
                    Team.TeamItem.TEAM_BADGE to teamsItem?.strTeamBadge,
                    Team.TeamItem.TEAM_NAME to teamsItem?.strTeam,
                    Team.TeamItem.TEAM_STADIUM to teamsItem?.strStadiumThumb,
                    Team.TeamItem.TEAM_LEAGUE to teamsItem?.strLeague,
                    Team.TeamItem.SPORT to teamsItem?.strSport
                )
            }
            snackBar(container_detail_team, R.string.add_to_favorite)
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Team.TeamItem.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to teamsItem?.idTeam!!
                )
            }
            snackBar(container_detail_team, R.string.remove_favorite)
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_added_to_favorites))
        } else {
            fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_add_to_favorites))
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Team.TeamItem.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to teamsItem?.idTeam!!)
            val favorite = result.parseList(classParser<Team.TeamItem>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        container_detail_team.visibility = View.GONE
        animation_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        container_detail_team.visibility = View.VISIBLE
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
