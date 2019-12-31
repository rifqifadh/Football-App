package com.example.rifqi.footballapp.features.Standings

import com.example.rifqi.footballapp.features.Standings.model.Standing
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingsPresenter(private var view: StandingsImpl.View) : StandingsImpl.Presenter {

    override fun getDataStandings(id: String, seasons: String) {
        view.showLoading()
        ApiConfig().instance().getLeagueStanding(id, seasons)
            .enqueue(object : Callback<Standing.StandingsResponse> {
                override fun onResponse(
                    call: Call<Standing.StandingsResponse>,
                    response: Response<Standing.StandingsResponse>
                ) {
                    val standingItem = response.body()?.table
                    if (standingItem != null) {
                        view.setDataStandings(standingItem)
                        view.hideLoading()
                    }
                }

                override fun onFailure(call: Call<Standing.StandingsResponse>, t: Throwable) {
                }
            })
    }
}