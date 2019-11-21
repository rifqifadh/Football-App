package com.example.rifqi.footballapp.features.DetailLeague

import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.DetailLeagueResponse
import com.example.rifqi.footballapp.model.Team
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeaguePresenter(
    private var view: DetailLeagueImpl.View
) : DetailLeagueImpl.Presenter {


    override fun getDataLeague(id: String) {
        view.showLoading()
        ApiConfig().instance().getDetailLeague(id)
            .enqueue(object : Callback<DetailLeagueResponse> {
                override fun onResponse(
                    call: Call<DetailLeagueResponse>,
                    response: Response<DetailLeagueResponse>
                ) {
                    val detailLeagueItem = response.body()?.leagues
                    if (response.isSuccessful) {
                        view.hideLoading()
                        view.setData(detailLeagueItem)
                    }
                }

                override fun onFailure(call: Call<DetailLeagueResponse>, t: Throwable) {
                    view.showErrorMessage("Request Time Out")
                }
            })
    }

    override fun getListTeams(leagues: String) {
        ApiConfig().instance().getListTeams(leagues)
            .enqueue(object : Callback<Team.Team> {
                override fun onResponse(call: Call<Team.Team>, response: Response<Team.Team>) {
                    val teams = response.body()?.teams
                    if (response.isSuccessful) {
                        teams?.let { view.setDataTeams(it) }
                    }
                }

                override fun onFailure(call: Call<Team.Team>, t: Throwable) {

                }
            })
    }
}