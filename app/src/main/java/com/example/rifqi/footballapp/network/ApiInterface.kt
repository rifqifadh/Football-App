package com.example.rifqi.footballapp.network

import com.example.rifqi.footballapp.features.DetailTeam.model.DetailTeam
import com.example.rifqi.footballapp.features.Standings.model.Standing
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.model.DetailLeagueResponse
import com.example.rifqi.footballapp.model.DetailMatch
import com.example.rifqi.footballapp.model.Match
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("json/1/lookupleague.php")
    fun getDetailLeague(@Query("id") id: String): Call<DetailLeagueResponse>

    @GET("json/1/eventspastleague.php")
    fun getListPreviousMatch(@Query("id") id: String): Call<Match.MatchResponse>

    @GET("json/1/eventsnextleague.php")
    fun getListNexyMatch(@Query("id") id: String): Call<Match.MatchResponse>

    @GET("json/1/searchevents.php")
    fun searchMatch(@Query("e") event: String): Call<Match.SearchMatchResponse>

    @GET("json/1/lookupevent.php")
    fun getDetailMatch(@Query("id") id: String): Call<DetailMatch.DetailMatch>

    @GET("json/1/lookupteam.php")
    fun getTeam(@Query("id") id: String): Call<Team.Team>

    @GET("json/1/search_all_teams.php")
    fun getListTeams(@Query("l") leagues: String): Call<Team.Team>

    @GET("json/1/lookuptable.php?")
    fun getLeagueStanding(@Query("l") leagues: String, @Query("s") season: String ): Call<Standing.StandingsResponse>

    @GET("json/1/lookup_all_teams.php")
    fun getAllTeams(@Query("id") id: String): Call<Team.Team>

    @GET("json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Call<DetailTeam.DetailTeamResponse>

    @GET("json/1/searchteams.php")
    fun searchTeam(@Query("t") query: String): Call<Team.Team>
}