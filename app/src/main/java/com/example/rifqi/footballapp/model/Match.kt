package com.example.rifqi.footballapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object Match {

    data class MatchResponse(
        var events: List<MatchItem>
    )

    @Parcelize
    data class MatchItem(
        val id: Long?,
        var idEvent: Int?,
        var strHomeTeam: String?,
        var intHomeScore: Int?,
        var strAwayTeam: String?,
        var intAwayScore: Int?,
        var dateEvent: String?,
        var strTime: String?,
        var strSport: String?
    ) : Parcelable {
        companion object {
            const val TABLE_FAVORITE_MATCH = "TABLE_FAVORITE_MATCH"
            const val ID_ = "ID_"
            const val EVENT_ID = "EVENT_ID"
            const val HOME_TEAM = "HOME_TEAM"
            const val HOME_SCORE = "HOME_SCORE"
            const val AWAY_TEAM = "AWAY_TEAM"
            const val AWAY_SCORE = "AWAY_SCORE"
            const val DATE_EVENT = "DATE_EVENT"
            const val TIME_EVENT = "TIME_EVENT"
            const val SPORT = "SPORT"
        }
    }

    data class SearchMatchResponse(
        var event: List<MatchItem>
    )
}