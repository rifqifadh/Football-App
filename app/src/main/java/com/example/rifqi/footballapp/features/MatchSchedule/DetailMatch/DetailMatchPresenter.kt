package com.example.rifqi.footballapp.features.MatchSchedule.DetailMatch

import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.model.DetailMatch
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchPresenter(private val view: DetailMatchImpl.View): DetailMatchImpl.Presenter {

    override fun getDetailMatch(id: String) {
        view.showLoading()
        ApiConfig().instance().getDetailMatch(id)
            .enqueue(object : Callback<DetailMatch.DetailMatch> {
                override fun onResponse(
                    call: Call<DetailMatch.DetailMatch>,
                    response: Response<DetailMatch.DetailMatch>
                ) {
                    val detailMatch = response.body()?.events
                    detailMatch?.let { view.setDetailMatch(it) }
                    view.hideLoading()
                }

                override fun onFailure(call: Call<DetailMatch.DetailMatch>, t: Throwable) {

                }
            })
    }

    override fun getDetailTeam(idHome: String, idAway: String) {
        ApiConfig().instance().getTeam(idHome)
            .enqueue(object : Callback<Team.Team> {
                override fun onResponse(call: Call<Team.Team>, response: Response<Team.Team>) {
                    val homeTeam = response.body()?.teams
                    view.setHomeBadge(homeTeam!!)
                }

                override fun onFailure(call: Call<Team.Team>, t: Throwable) {

                }
            })

        ApiConfig().instance().getTeam(idAway)
            .enqueue(object : Callback<Team.Team> {
                override fun onResponse(call: Call<Team.Team>, response: Response<Team.Team>) {
                    val awayTeam = response.body()?.teams
                    view.setAwayBadge(awayTeam!!)
                }

                override fun onFailure(call: Call<Team.Team>, t: Throwable) {

                }
            })
    }
}