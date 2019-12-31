package com.example.rifqi.footballapp.features.Teams

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.DetailTeam.DetailTeamActivity
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.utils.Constants.Companion.TEAM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.startActivity

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
        holder.itemView.setOnClickListener {
            context.startActivity<DetailTeamActivity>(TEAM to teamsItem)
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(teamItem: Team.TeamItem) {
        itemView.tv_teams_name.text = teamItem.strTeam
        Picasso.get().load(teamItem.strTeamBadge)
            .fit().into(itemView.img_team)
    }
}