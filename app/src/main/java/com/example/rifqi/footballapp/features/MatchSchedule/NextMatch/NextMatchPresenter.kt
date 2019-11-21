package com.example.rifqi.footballapp.features.MatchSchedule.NextMatch

import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchPresenter(private var view: NextMatchImpl.View) :
    NextMatchImpl.Presenter {

    override fun getListMatch(id: String) {
        view.showLoading()
        ApiConfig().instance().getListNexyMatch(id)
            .enqueue(object : Callback<Match.MatchResponse> {
                override fun onResponse(
                    call: Call<Match.MatchResponse>,
                    response: Response<Match.MatchResponse>
                ) {
                    val listMatch = response.body()?.events
                    if (response.isSuccessful) {
                        listMatch?.let { view.setDataList(it) }
                        view.hideLoading()
                    } else {
                        view.showErrorMessage("Error Request Data")
                    }
                }

                override fun onFailure(call: Call<Match.MatchResponse>, t: Throwable) {
                    view.showErrorMessage("Request Time Out")
                }
            })
    }
}