package com.example.rifqi.footballapp.features.Teams.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object Team {

    data class Team(
        var teams: List<TeamItem>? = null
    )

    @Parcelize
    data class TeamItem(
        val id: Long?,
        var idTeam: String?,
        var strTeamBadge: String?,
        var strTeam: String?,
        var strStadiumThumb: String?,
        var strLeague: String?,
        var strSport: String?
    ): Parcelable {
        companion object {
            const val TABLE_FAVORITE_TEAM = "TABLE_FAVORITE_TEAM"
            const val ID_ = "ID_"
            const val TEAM_ID = "TEAM_ID"
            const val TEAM_BADGE = "TEAM_BADGE"
            const val TEAM_NAME = "TEAM_NAME"
            const val TEAM_STADIUM = "TEAM_STADIUM"
            const val TEAM_LEAGUE = "TEAM_LEAGUE"
            const val SPORT = "SPORT"
        }
    }
}