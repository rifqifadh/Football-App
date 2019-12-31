package com.example.rifqi.footballapp.features.MatchSchedule.NextMatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rifqi.footballapp.R
import com.example.rifqi.footballapp.features.MatchSchedule.MatchAdapter
import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.loading.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(),  NextMatchImpl.View {

    private var matchItems: MutableList<Match.MatchItem> = mutableListOf()
    private var leagueId: String? = null

    private val presenter by lazy { NextMatchPresenter(this) }
    private val adapter by lazy { MatchAdapter(matchItems, context!!) }

    fun newInstance(leagueId: String): NextMatchFragment {
        val bundle = Bundle()
        bundle.putString("leagueId", leagueId)

        val fragment = NextMatchFragment()
        fragment.arguments = bundle

        return fragment
    }

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            leagueId = bundle.getString("leagueId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next, container, false)
        readBundle(arguments)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EspressoIdlingResource.increment()
        leagueId?.let { presenter.getListMatch(it) }
        rv_next_match.layoutManager = LinearLayoutManager(context)
        rv_next_match.adapter = adapter
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
        animation_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        animation_loading.visibility = View.GONE
    }

    override fun showErrorMessage(message: String?) {
        toast(message.toString())
    }
}
