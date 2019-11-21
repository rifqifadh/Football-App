package com.example.rifqi.footballapp.features.MatchSchedule.PrevMatch

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.Match

interface PrevMatchImpl {

    interface Presenter {
        fun getListMatch(id: String)
    }

    interface View: BaseView {
        fun setDataList(data: List<Match.MatchItem>)
    }
}