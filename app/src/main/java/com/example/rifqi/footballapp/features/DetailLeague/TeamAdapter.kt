package com.example.rifqi.footballapp.features.DetailLeague

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*

class TeamAdapter(private val teams: List<Team.TeamItem>, private val context: Context) :
    RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teams, parent, false
            )
        )
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teamsItem = teams[position]
        holder.bindItem(teamsItem)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(teamItem: Team.TeamItem) {
        Picasso.get().load(teamItem.strTeamBadge)
            .fit().into(itemView.img_team)
    }
}