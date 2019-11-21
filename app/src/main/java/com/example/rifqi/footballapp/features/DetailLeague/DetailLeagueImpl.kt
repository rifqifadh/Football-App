package com.example.rifqi.footballapp.features.DetailLeague

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.Team

interface DetailLeagueImpl {

    interface Presenter {
        fun getDataLeague(id: String)
        fun getListTeams(leagues: String)
    }

    interface View: BaseView {
        fun setData(data: List<DetailLeagueItem>?)
        fun setDataTeams(data: List<Team.TeamItem>)
    }
}