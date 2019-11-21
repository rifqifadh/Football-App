package com.example.rifqi.footballapp.features.Favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.DetailMatch.DetailMatchActivity
import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.utils.Constants.Companion.EVENT_ID
import com.example.rifqi.footballapp.utils.DateTime
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class FavoritesAdapter(
    private val favorite: List<Match.MatchItem>,
    private val context: Context
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_match, parent, false
            )
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteItem = favorite[position]
        holder.bindItem(favoriteItem)
        holder.itemView.setOnClickListener {
            context.startActivity<DetailMatchActivity>(EVENT_ID to favoriteItem)
        }
    }
}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindItem(matchItem: Match.MatchItem) {
        try {
            itemView.tv_date.text = matchItem.dateEvent?.let { DateTime().dateFormat(it) }
            itemView.tv_time.text = matchItem.strTime?.let { DateTime().timeFormat(it) }
        } catch (e: Exception) {
        }
        itemView.tv_home_team.text = matchItem.strHomeTeam
        itemView.tv_away_team.text = matchItem.strAwayTeam
        val homeScore = matchItem.intHomeScore?.let { it } ?: "?"
        val awayScore = matchItem.intAwayScore?.let { it } ?: "?"
        itemView.tv_score_home.text = homeScore.toString()
        itemView.tv_score_away.text = awayScore.toString()

    }
}
