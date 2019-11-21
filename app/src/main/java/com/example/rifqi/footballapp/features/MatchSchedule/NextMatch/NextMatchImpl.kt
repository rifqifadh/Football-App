package com.example.rifqi.footballapp.features.MatchSchedule.NextMatch

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.Match

interface NextMatchImpl {

    interface Presenter {
        fun getListMatch(id: String)
    }

    interface View: BaseView {
        fun setDataList(data: List<Match.MatchItem>)
    }
}