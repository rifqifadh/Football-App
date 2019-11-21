package com.example.rifqi.footballapp.features.Leagues


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.model.LeagueItem
import org.jetbrains.anko.support.v4.find

/**
 * A simple [Fragment] subclass.
 */
class LeaguesFragment : Fragment() {

    private var leagueItems: MutableList<LeagueItem> = mutableListOf()
    private val adapter by lazy { context?.let { LeagueAdapter(leagueItems, it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = find<RecyclerView>(R.id.rv_list_league)
        initData()
        list.layoutManager = GridLayoutManager(context, 2)
        list.adapter = adapter
    }


    private fun initData() {
        val name = resources.getStringArray(R.array.league)
        val image = resources.obtainTypedArray(R.array.badge_image)
        val id = resources.getStringArray(R.array.league_id)
        leagueItems.clear()
        for (i in name.indices) {
            leagueItems.add(
                LeagueItem(
                    name[i],
                    image.getResourceId(i, 0),
                    id[i]
                )
            )
        }
    }
}
