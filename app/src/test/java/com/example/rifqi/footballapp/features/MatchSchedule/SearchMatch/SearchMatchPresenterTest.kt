package com.example.rifqi.footballapp.features.MatchSchedule.SearchMatch

import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.network.ApiInterface
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doAnswer
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchMatchPresenterTest {

    @Mock
    private lateinit var view: SearchMatchImpl.View

    @Mock
    private lateinit var apiInterface: ApiInterface


    private lateinit var presenter: SearchMatchPresenter
    private val queryText = "Man United"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view)
    }

    @Test
    fun searchMatch() {
        val mockedCall: Call<Match.SearchMatchResponse> = mock()
        val mockedResponse: Match.SearchMatchResponse = mock()
        val mockedResponseItem: List<Match.MatchItem> = mock()

        presenter.searchMatch(queryText)

        `when`(apiInterface.searchMatch(queryText)).thenReturn(mockedCall)

        doAnswer {
            val callback: Callback<Match.SearchMatchResponse> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).setDataList(mockedResponseItem)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())
    }
}