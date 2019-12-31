package com.example.rifqi.footballapp.features.MatchSchedule.DetailMatch

import com.example.rifqi.footballapp.model.DetailMatch
import com.example.rifqi.footballapp.features.Teams.model.Team
import com.example.rifqi.footballapp.network.ApiInterface
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doAnswer
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchImpl.View

    @Mock
    private lateinit var apiInterface: ApiInterface

    private lateinit var presenter: DetailMatchPresenter
    private val id = "441613"
    private val idHomeTeam = "133602"
    private val idAwayTeam = "133614"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view)
    }

    @Test
    fun getDetailMatch() {
        val mockedCall: Call<DetailMatch.DetailMatch> = mock()
        val mockedResponse: DetailMatch.DetailMatch = mock()
        val mockedResponseItem: List<DetailMatch.DetailMatchItem> = mock()

        presenter.getDetailMatch(id)
        verify(view).showLoading()

        `when`(apiInterface.getDetailMatch(id)).thenReturn(mockedCall)

        doAnswer {
            val callback: Callback<DetailMatch.DetailMatch> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).setDetailMatch(mockedResponseItem)
            verify(view).hideLoading()
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())
    }

    @Test
    fun getDetailTeam() {
        val mockedCall: Call<Team.Team> = mock()
        val mockedResponse: Team.Team = mock()
        val mockedResponseItem: List<Team.TeamItem> = mock()

        presenter.getDetailTeam(idHomeTeam, idAwayTeam)

        `when`(apiInterface.getTeam(idHomeTeam)).thenReturn(mockedCall)
        `when`(apiInterface.getTeam(idAwayTeam)).thenReturn(mockedCall)

        doAnswer {
            val callback: Callback<Team.Team> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).setHomeBadge(mockedResponseItem)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())

        doAnswer {
            val callback: Callback<Team.Team> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).setAwayBadge(mockedResponseItem)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())
    }
}