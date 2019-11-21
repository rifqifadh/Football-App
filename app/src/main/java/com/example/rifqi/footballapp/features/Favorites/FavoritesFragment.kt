package com.example.rifqi.footballapp.features.Favorites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.helper.database
import com.example.rifqi.footballapp.model.Match
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment(), AnkoLogger {

    private var favorites: MutableList<Match.MatchItem> = mutableListOf()
    private val adapter by lazy { FavoritesAdapter(favorites, context!!) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rv_favorites_match.layoutManager = LinearLayoutManager(context)
        showFavorites()
        rv_favorites_match.adapter = adapter

        swipe_refresh.setOnRefreshListener {
            showFavorites()
            swipe_refresh.isRefreshing = false
        }

    }

    override fun onResume() {
        super.onResume()
        showFavorites()
    }

    private fun showFavorites() {
        favorites.clear()
        context?.database?.use {
            val result = select(Match.MatchItem.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<Match.MatchItem>())
            if (favorite.isEmpty()) {
                empty_box.visibility = View.VISIBLE
            } else {
                empty_box.visibility = View.GONE
            }
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
