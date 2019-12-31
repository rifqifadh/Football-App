package com.example.rifqi.footballapp.features.Standings

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.features.Standings.model.Standing

interface StandingsImpl {

    interface Presenter {
        fun getDataStandings(id: String, seasons: String)
    }

    interface View: BaseView {
        fun setDataStandings(data: List<Standing.Standings>)
    }
}