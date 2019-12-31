package com.example.rifqi.footballapp.features.DetailTeam

import com.example.rifqi.footballapp.Base.BaseView
import com.example.rifqi.footballapp.features.DetailTeam.model.DetailTeam

interface DetailTeamImpl {

    interface Presenter {
        fun getDetailTeam(id: String)
    }

    interface View: BaseView {
        fun setDetailTeam(data: List<DetailTeam.DetailTeam>?)
    }
}