package com.example.rifqi.footballapp.features.Teams.Search

import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTeamPresenter(private val view: SearchTeamImpl.View) : SearchTeamImpl.Presenter {

    override fun getSearchTeams(query: String) {
        ApiConfig().instance().searchTeam(query).enqueue(object : Callback<Team.Team> {
            override fun onResponse(call: Call<Team.Team>, response: Response<Team.Team>) {
                val item = response.body()?.teams
                if (item != null ) {
                    item.filter { it.strSport == "Soccer" }
                    view.setSearchTeams(item)
                }
            }

            override fun onFailure(call: Call<Team.Team>, t: Throwable) {

            }
        })
    }
}