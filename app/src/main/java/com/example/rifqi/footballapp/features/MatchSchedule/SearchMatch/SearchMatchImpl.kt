package com.example.rifqi.footballapp.features.MatchSchedule.SearchMatch

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.model.Match

interface SearchMatchImpl {

    interface Presenter {
        fun searchMatch(event: String)
    }

    interface View: BaseView {
        fun setDataList(data: List<Match.MatchItem>)
    }
}