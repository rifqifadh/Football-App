package com.example.rifqi.footballapp.features.MatchSchedule.SearchMatch

import android.util.Log
import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchPresenter(private var view: SearchMatchImpl.View) :
    SearchMatchImpl.Presenter {

    override fun searchMatch(event: String) {
        ApiConfig().instance().searchMatch(event)
            .enqueue(object : Callback<Match.SearchMatchResponse> {
                override fun onResponse(
                    call: Call<Match.SearchMatchResponse>,
                    response: Response<Match.SearchMatchResponse>
                ) {
                    val listMatch = response.body()?.event
                    if (listMatch != null) {
                        val data = listMatch.filter { it.strSport == "Soccer" }
                        view.setDataList(data)
                    }
                }

                override fun onFailure(call: Call<Match.SearchMatchResponse>, t: Throwable) {

                }
            })
    }

}