package com.example.rifqi.footballapp.features.Leagues

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.DetailLeague.DetailLeagueActivity
import com.example.rifqi.footballapp.model.LeagueItem
import com.example.rifqi.footballapp.utils.Constants.Companion.LEAGUE_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*
import org.jetbrains.anko.startActivity

class LeagueAdapter(private val leagueItems: List<LeagueItem>, private val context: Context):
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_league,
            parent, false))
    }

    override fun getItemCount(): Int = leagueItems.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val leagueItem: LeagueItem = leagueItems[position]
        holder.bindItem(leagueItem)
        holder.itemView.setOnClickListener {
            context.startActivity<DetailLeagueActivity>(LEAGUE_ID to leagueItem)
        }
    }


    inner class LeagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItem(leagueItem: LeagueItem) {
            itemView.txt_league.text = leagueItem.name
            leagueItem.image?.let { Picasso.get().load(it).fit().into(itemView.img_league) }
        }
    }
}