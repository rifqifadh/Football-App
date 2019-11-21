package com.example.rifqi.footballapp.model

object Team {

    data class Team(
        var teams: List<TeamItem>? = null
    )

    data class TeamItem(
        var strTeamBadge: String?
    )
}