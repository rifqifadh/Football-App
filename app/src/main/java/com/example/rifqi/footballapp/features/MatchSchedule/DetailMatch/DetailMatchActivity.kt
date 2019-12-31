package com.example.rifqi.footballapp.features.MatchSchedule.DetailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.helper.database
import com.example.rifqi.footballapp.model.DetailMatch
import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.utils.Constants.Companion.EVENT_ID
import com.example.rifqi.footballapp.utils.DateTime
import com.example.rifqi.footballapp.utils.Functions.replaceCharacter
import com.example.rifqi.footballapp.utils.Functions.snackBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchActivity : AppCompatActivity(), DetailMatchImpl.View {

    private val presenter by lazy { DetailMatchPresenter(this) }
    private var matchItem: Match.MatchItem? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Match"

        matchItem = intent.getParcelableExtra(EVENT_ID)
        val eventId = matchItem?.idEvent.toString()
        favoriteState()

        eventId.let { presenter.getDetailMatch(it) }
    }

    override fun setDetailMatch(data: List<DetailMatch.DetailMatchItem>) {
        data.map { it ->
            presenter.getDetailTeam(it.idHomeTeam ?: "", it.idAwayTeam ?: "")
            val date = DateTime().dateFormat(it.dateEvent)
            val time = DateTime().timeFormat(it.strTime)
            tv_date_time.text = resources.getString(R.string.dateTime, date, time)

            val homeScore = it.intHomeScore?.let { it } ?: "?"
            val awayScore = it.intAwayScore?.let { it } ?: "?"

            tv_score.text = resources.getString(R.string.score, homeScore, awayScore)

            tv_club_home.text = it.strHomeTeam
            tv_club_away.text = it.strAwayTeam

            tv_home_goal.text = it.strHomeGoalDetails?.let { it1 -> replaceCharacter(it1) }
            tv_away_goal.text = it.strAwayGoalDetails?.let { it1 -> replaceCharacter(it1) }

            home_red_card.text = it.strHomeRedCards?.let { it1 -> replaceCharacter(it1) }
            away_red_card.text = it.strAwayRedCards?.let { it1 -> replaceCharacter(it1) }

            home_yellow_card.text = it.strHomeYellowCards?.let { it1 -> replaceCharacter(it1) }
            away_yellow_card.text = it.strAwayYellowCards?.let { it1 -> replaceCharacter(it1) }

            home_formation.text = it.strHomeFormation?.let { it } ?: "-"
            home_goalkeeper.text = it.strHomeLineupGoalkeeper?.let { it1 -> replaceCharacter(it1) }
            home_defense.text = it.strHomeLineupDefense?.let { it1 -> replaceCharacter(it1) }
            home_midfield.text = it.strHomeLineupMidfield?.let { it1 -> replaceCharacter(it1) }
            home_forward.text = it.strHomeLineupForward?.let { it1 -> replaceCharacter(it1) }

            away_formation.text = it.strAwayFormation?.let { it } ?: "-"
            away_goalkeeper.text = it.strAwayLineupGoalkeeper?.let { it1 -> replaceCharacter(it1) }
            away_defense.text = it.strAwayLineupDefense?.let { it1 -> replaceCharacter(it1) }
            away_midfield.text = it.strAwayLineupMidfield?.let { it1 -> replaceCharacter(it1) }
            away_forward.text = it.strAwayLineupForward?.let { it1 -> replaceCharacter(it1) }
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Match.MatchItem.TABLE_FAVORITE_MATCH,
                    Match.MatchItem.EVENT_ID to matchItem?.idEvent,
                    Match.MatchItem.HOME_TEAM to matchItem?.strHomeTeam,
                    Match.MatchItem.HOME_SCORE to matchItem?.intHomeScore,
                    Match.MatchItem.AWAY_TEAM to matchItem?.strAwayTeam,
                    Match.MatchItem.AWAY_SCORE to matchItem?.intAwayScore,
                    Match.MatchItem.DATE_EVENT to matchItem?.dateEvent,
                    Match.MatchItem.TIME_EVENT to matchItem?.strTime,
                    Match.MatchItem.SPORT to matchItem?.strSport
                )
            }
            snackBar(root_detail, R.string.add_to_favorite)
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Match.MatchItem.TABLE_FAVORITE_MATCH)
                .whereArgs("(EVENT_ID = {id})", "id" to matchItem?.idEvent!!)
            val favorite = result.parseList(classParser<Match.MatchItem>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Match.MatchItem.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
                    "id" to matchItem?.idEvent!!
                )
            }
            snackBar(root_detail, R.string.remove_favorite)
        } catch (e: SQLiteConstraintException) {

        }
    }

    override fun setHomeBadge(data: List<Team.TeamItem>) {
        Picasso.get().load(data[0].strTeamBadge)
            .fit().into(img_club_home)
    }

    override fun setAwayBadge(data: List<Team.TeamItem>) {
        Picasso.get().load(data[0].strTeamBadge)
            .fit().into(img_club_away)
    }

    override fun showLoading() {
        animation_loading.visibility = View.VISIBLE
        parent_layout.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        animation_loading.visibility = View.INVISIBLE
        parent_layout.visibility = View.VISIBLE
    }

    override fun showErrorMessage(message: String?) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
