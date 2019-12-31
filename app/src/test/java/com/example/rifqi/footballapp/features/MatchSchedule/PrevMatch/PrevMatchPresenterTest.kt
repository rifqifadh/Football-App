package com.example.rifqi.footballapp.features.MatchSchedule.PrevMatch

import com.example.rifqi.footballapp.model.Match
import com.example.rifqi.footballapp.network.ApiInterface
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevMatchPresenterTest {

    @Mock
    private lateinit var view: PrevMatchImpl.View

    @Mock
    private lateinit var apiInterface: ApiInterface

    private lateinit var presenter: PrevMatchPresenter

    private val errorMessage = "Request Time Out"
    private val id = "4328"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchPresenter(view)
    }

    @Test
    fun getListMatch() {
        val mockedCall: Call<Match.MatchResponse> = mock()
        val mockedResponse: Match.MatchResponse = mock()
        val mockedResponseItem: List<Match.MatchItem> = mock()

        presenter.getListMatch(id)
        verify(view).showLoading()

        `when`(apiInterface.getListPreviousMatch(id)).thenReturn(mockedCall)

        doAnswer {
            val callback: Callback<Match.MatchResponse> = it.getArgument(0)
            callback.onResponse(mockedCall, Response.success(mockedResponse))
            verify(view).setDataList(mockedResponseItem)
            verify(view).hideLoading()
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())

        doAnswer {
            val t = Throwable()
            val callback: Callback<Match.MatchResponse> = it.getArgument(0)
            callback.onFailure(mockedCall, t)
            verify(view).showErrorMessage(errorMessage)
        }.`when`(mockedCall).enqueue(ArgumentMatchers.any())
    }
}