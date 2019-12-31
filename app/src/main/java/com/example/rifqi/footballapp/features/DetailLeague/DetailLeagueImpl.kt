package com.example.rifqi.footballapp.features.DetailLeague

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.DetailLeagueItem

interface DetailLeagueImpl {

    interface Presenter {
        fun getDataLeague(id: String)
    }

    interface View: BaseView {
        fun setData(data: List<DetailLeagueItem>?)
    }
}