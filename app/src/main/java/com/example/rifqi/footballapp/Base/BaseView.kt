package com.example.rifqi.footballapp.Base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage(message:String?)
}