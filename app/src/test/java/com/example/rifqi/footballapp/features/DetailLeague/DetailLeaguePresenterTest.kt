package com.example.rifqi.footballapp.features.DetailLeague

import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.DetailLeagueResponse
import com.example.rifqi.footballapp.network.ApiConfig
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

import org.mockito.Mockito.`when`
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeaguePresenterTest {

    @Mock
    private lateinit var view: DetailLeagueImpl.View

    @Mock
    private lateinit var detailLeague: DetailLeagueItem

    @Mock
    private lateinit var apiConfig: ApiConfig

    private lateinit var presenter: DetailLeaguePresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view)

    }

    @Test
    fun getDataLeague() {
        val mockedCall: Call<DetailLeagueResponse> = mock()
        val mockedResponse: DetailLeagueResponse = mock()
        val id = "4328"

        doAnswer {
            val callback: Callback<DetailLeagueResponse> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())

        presenter.getListTeams(id)


    }

    @Test
    fun getListTeams() {
    }
}