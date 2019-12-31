package com.example.rifqi.footballapp.features.DetailTeam

import com.example.rifqi.footballapp.features.DetailTeam.model.DetailTeam
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTeamPresenter(private val view: DetailTeamImpl.View): DetailTeamImpl.Presenter {

    override fun getDetailTeam(id: String) {
        view.showLoading()
        ApiConfig().instance().getDetailTeam(id).enqueue(object : Callback<DetailTeam.DetailTeamResponse> {
            override fun onResponse(
                call: Call<DetailTeam.DetailTeamResponse>,
                response: Response<DetailTeam.DetailTeamResponse>
            ) {
                val item = response.body()?.teams
                if (response.isSuccessful) {
                    view.hideLoading()
                    view.setDetailTeam(item)
                }
            }

            override fun onFailure(call: Call<DetailTeam.DetailTeamResponse>, t: Throwable) {

            }
        })
    }
}