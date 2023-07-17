package com.example.mvpexample.presenter

import android.os.Handler
import com.example.mvpexample.model.Dog
import com.example.mvpexample.model.DogListData
import kotlinx.coroutines.*

class SearchPresenter : SearchContract.Presenter {

    private var searchView : SearchContract.View? = null

    override fun getDogList() {
        searchView?.showLoading()

        CoroutineScope(Dispatchers.Main).launch {

            // 리스트 추가
            val dogList = getDogListData()

            // 후처리 코드
            searchView?.showDogList(dogList)
            searchView?.hideLoading()
        }
    }

    override suspend fun getDogListData(): List<Dog> {
        val data = DogListData.getDoglistData()
        delay(1000L) // 테스트용 (통신하는척)
        return data
    }

    override fun takeView(view: SearchContract.View) {
        searchView = view
    }

    override fun dropView() {
        searchView = null
    }


}