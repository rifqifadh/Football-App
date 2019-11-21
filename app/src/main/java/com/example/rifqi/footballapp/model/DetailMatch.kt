package com.example.rifqi.footballapp.model

object DetailMatch {

    data class DetailMatch(
        var events: List<DetailMatchItem>
    )

    data class DetailMatchItem(
        var idEvent: String?,
        var strHomeTeam: String?,
        var strAwayTeam: String?,
        var intHomeScore: String?,
        var intAwayScore: String?,
        var strHomeGoalDetails: String?,
        var strAwayGoalDetails: String?,
        var strHomeFormation: String?,
        var strAwayFormation: String?,
        var dateEvent: String?,
        var strTime: String?,
        var strHomeYellowCards: String?,
        var strHomeRedCards: String?,
        var strAwayYellowCards: String?,
        var strAwayRedCards: String?,
        var idHomeTeam: String?,
        var idAwayTeam: String?,
        var strAwayLineupGoalkeeper: String?,
        var strAwayLineupDefense: String?,
        var strAwayLineupMidfield: String?,
        var strAwayLineupForward: String?,
        var strHomeLineupGoalkeeper: String?,
        var strHomeLineupDefense: String?,
        var strHomeLineupMidfield: String?,
        var strHomeLineupForward: String?
    )
}