package com.example.rifqi.footballapp.features.DetailLeague

import com.example.rifqi.footballapp.model.DetailLeagueItem
import com.example.rifqi.footballapp.model.DetailLeagueResponse
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.network.ApiInterface
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
    private lateinit var apiInterface: ApiInterface


    private lateinit var presenter: DetailLeaguePresenter

    private val errorMessage = "Request Time Out"


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view)
    }

    @Test
    fun getDataLeague() {
        val mockedCall: Call<DetailLeagueResponse> = mock()
        val mockedResponse: DetailLeagueResponse = mock()
        val mockedResponseItem: List<DetailLeagueItem> = mock()
        val id = "4328"


        presenter.getDataLeague(id)
        verify(view).showLoading()

        `when`(apiInterface.getDetailLeague(id)).thenReturn(mockedCall)

        doAnswer {
            val callback: Callback<DetailLeagueResponse> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).hideLoading()
            verify(view).setData(mockedResponseItem)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())

        doAnswer {
            val t = Throwable()
            val callback: Callback<DetailLeagueResponse> = it.getArgument(0)
            callback.onFailure(mockedCall, t)
            verify(view).showErrorMessage(errorMessage)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())

    }
}