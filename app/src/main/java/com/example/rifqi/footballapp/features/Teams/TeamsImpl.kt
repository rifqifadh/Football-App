package com.example.rifqi.footballapp.features.Teams

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.features.Teams.model.Team

interface TeamsImpl {

    interface Presenter {
        fun getListTeams(id: String)
    }

    interface View: BaseView {
        fun setListTeams(data: List<Team.TeamItem>)
    }
}