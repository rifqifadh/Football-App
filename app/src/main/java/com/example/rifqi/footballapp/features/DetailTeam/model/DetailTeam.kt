package com.example.rifqi.footballapp.features.DetailTeam.model

object DetailTeam {
    data class DetailTeamResponse(
        var teams: List<DetailTeam>
    )

    data class DetailTeam(
        var strLeague: String?,
        var strStadium: String?,
        var strStadiumDescription: String?,
        var strStadiumLocation: String?,
        var strWebsite: String?,
        var strFacebook: String?,
        var strTwitter: String?,
        var strInstagram: String?,
        var strDescriptionEN: String?,
        var strYoutube: String?
    )
}