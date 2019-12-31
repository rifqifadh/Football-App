package com.example.rifqi.footballapp.features.Standings.model

object Standing {

    data class Standings (
        var name: String?,
        var played: Int?,
        var win: Int?,
        var draw: Int?,
        var loss: Int?,
        var total: Int?,
        var teamid: Int?
    )

    data class StandingsResponse (
        var table: List<Standings>
    )

}