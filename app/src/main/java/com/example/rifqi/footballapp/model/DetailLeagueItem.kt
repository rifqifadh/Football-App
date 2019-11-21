package com.example.rifqi.footballapp.model

import com.google.gson.annotations.SerializedName

data class DetailLeagueItem (

    var idLeague: String?,
    var strLeague: String?,
    var intFormedYear: String?,
    var strCountry: String?,
    var strWebsite: String,
    var strFacebook: String,
    var strTwitter: String?,
    @SerializedName("strDescriptionEN")
    var strDescription: String?,
    var strBanner: String?,
    var strFanart1: String?,
    var strLogo: String?
)