package com.example.rifqi.footballapp.features.MatchSchedule.DetailMatch

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.DetailMatch
import com.example.rifqi.footballapp.model.Team

interface DetailMatchImpl {

    interface Presenter {
        fun getDetailMatch(id: String)
        fun getDetailTeam(idHome: String, idAway: String)
    }

    interface View: BaseView {
        fun setDetailMatch(data: List<DetailMatch.DetailMatchItem>)
        fun setHomeBadge(data: List<Team.TeamItem>)
        fun setAwayBadge(data: List<Team.TeamItem>)
    }

}