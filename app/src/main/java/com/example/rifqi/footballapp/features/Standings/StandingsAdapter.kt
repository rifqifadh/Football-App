package com.example.rifqi.footballapp.features.Standings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Standings.model.Standing
import kotlinx.android.synthetic.main.item_standings.view.*

class StandingsAdapter(
    private val listItems: List<Standing.Standings>,
    private val context: Context
) : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_standings, parent, false
            )
        )
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val standingsItem = listItems[position]
        holder.bindItem(standingsItem, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(standingsItem: Standing.Standings, position: Int) {
            val pos = position + 1
            itemView.tv_position.text = pos.toString()
            itemView.tv_team_name_standings.text = standingsItem.name
            itemView.tv_total_standings.text = standingsItem.total.toString()
            itemView.tv_win_standings.text = standingsItem.win.toString()
            itemView.tv_draw_standings.text = standingsItem.draw.toString()
            itemView.tv_lose_standings.text = standingsItem.loss.toString()
            itemView.tv_play_standings.text = standingsItem.played.toString()
        }
    }
}