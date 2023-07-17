package com.example.mvpexample.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mvpexample.base.BaseActivity
import com.example.mvpexample.databinding.ActivityMainBinding
import com.example.mvpexample.model.Dog
import com.example.mvpexample.presenter.SearchContract
import com.example.mvpexample.presenter.SearchPresenter

class MainActivity : BaseActivity(), SearchContract.View {

    private lateinit var searchPresenter: SearchPresenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        searchPresenter.takeView(this)

        setButton()
    }

    override fun onDestroy() {
        super.onDestroy()

        searchPresenter.dropView()
    }

    private fun setButton() {
        binding.getDogListButton.setOnClickListener {
            clearText()
            searchPresenter.getDogList()
        }
    }

    override fun initPresenter() {
        searchPresenter = SearchPresenter()
    }

    override fun showLoading() {
        binding.searchRefresh.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.searchRefresh.visibility = View.GONE
    }

    override fun showDogList(dogList: List<Dog>) {
        binding.firstDogText.text = "Name : ${dogList[0].name}, Age : ${dogList[0].age}"
        binding.secondDogText.text = "Name : ${dogList[1].name}, Age : ${dogList[1].age}"
        binding.thirdDogText.text = "Name : ${dogList[2].name}, Age : ${dogList[2].age}"
    }

    override fun clearText() {
        binding.firstDogText.text = ""
        binding.secondDogText.text = ""
        binding.thirdDogText.text = ""
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

}