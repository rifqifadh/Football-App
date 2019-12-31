package com.example.rifqi.footballapp.features.Teams.Search

import com.example.rifqi.footballapp.features.Teams.model.Team

interface SearchTeamImpl {

    interface Presenter {
        fun getSearchTeams(query: String)
    }

    interface View {
        fun setSearchTeams(data: List<Team.TeamItem>)
    }
}