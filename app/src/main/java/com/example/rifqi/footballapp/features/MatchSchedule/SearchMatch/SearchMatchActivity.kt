package com.example.rifqi.footballapp.features.MatchSchedule.SearchMatch

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.MatchAdapter
import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_search_match.*

class SearchMatchActivity : AppCompatActivity(), SearchMatchImpl.View {

    private var matchItems: MutableList<Match.MatchItem> = mutableListOf()
    private val adapter by lazy {
        MatchAdapter(
            matchItems,
            this
        )
    }
    private val presenter by lazy { SearchMatchPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        rv_match_search.layoutManager = LinearLayoutManager(baseContext)
        rv_match_search.adapter = adapter
    }

    override fun setDataList(data: List<Match.MatchItem>) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        matchItems.clear()
        matchItems.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showErrorMessage(message: String?) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                EspressoIdlingResource.increment()
                presenter.searchMatch(newText?.replace(" ", "_").toString())
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                EspressoIdlingResource.increment()
                presenter.searchMatch(query?.replace(" ", "_").toString())
                return true
            }
        })
        menu.findItem(R.id.action_search).expandActionView()

        return true
    }

}
