package com.example.rifqi.footballapp.features.Teams

import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsPresenter(private var view: TeamsImpl.View) : TeamsImpl.Presenter {

    override fun getListTeams(id: String) {
        view.showLoading()
        ApiConfig().instance().getAllTeams(id)
            .enqueue(object : Callback<Team.Team> {
                override fun onResponse(call: Call<Team.Team>, response: Response<Team.Team>) {
                    val teamsItem = response.body()?.teams
                    if (teamsItem != null) {
                        view.setListTeams(teamsItem)
                        view.hideLoading()
                    }
                }

                override fun onFailure(call: Call<Team.Team>, t: Throwable) {

                }
            })
    }

}