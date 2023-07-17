package com.example.mvpexample.presenter

import com.example.mvpexample.base.BasePresenter
import com.example.mvpexample.base.BaseView
import com.example.mvpexample.model.Dog

interface SearchContract {

    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun showDogList(dogList: List<Dog>)
        fun clearText()
    }

    interface Presenter : BasePresenter<View> {
        fun getDogList()
        suspend fun getDogListData(): List<Dog>
    }

}