package com.example.rifqi.footballapp.features.Favorites.FavoriteTeam

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

class FavoriteTeamAdapter(
    private val favorites: List<Team.TeamItem>,
    private val context: Context
) : RecyclerView.Adapter<FavTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamViewHolder {
        return FavTeamViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teams, parent, false
            )
        )
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavTeamViewHolder, position: Int) {
        val favoriteItem = favorites[position]
        holder.bindItem(favoriteItem)
        holder.itemView.setOnClickListener {
            context.startActivity<DetailTeamActivity>(TEAM to favoriteItem)
        }
    }

}

class FavTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(teamsItem: Team.TeamItem) {
        itemView.tv_teams_name.text = teamsItem.strTeam
        Picasso.get().load(teamsItem.strTeamBadge).fit().into(itemView.img_team)
    }

}
