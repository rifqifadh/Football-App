package com.example.rifqi.footballapp.features.Favorites.FavoriteTeam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.helper.database
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritesTeamFragment : Fragment() {

    private var favorites: MutableList<Team.TeamItem> = mutableListOf()
    private val adapter by lazy { FavoriteTeamAdapter(favorites, context!!) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_favorites_team.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        showFavorites()
        rv_favorites_team.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        showFavorites()
    }

    private fun showFavorites() {
        favorites.clear()
        context?.database?.use {
            val result = select(Team.TeamItem.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<Team.TeamItem>())
            if (favorite.isEmpty()) {
                empty_item.visibility = View.VISIBLE
            } else {
                empty_item.visibility = View.GONE
            }
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
