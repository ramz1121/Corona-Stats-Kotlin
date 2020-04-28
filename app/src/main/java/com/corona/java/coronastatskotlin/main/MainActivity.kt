package com.corona.java.coronastatskotlin.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.corona.java.coronastatskotlin.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CaseNumbersInteractor.mainView {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        llProgressBar?.visibility = View.VISIBLE
        presenter?.networkcall()
        //swipe to refresh listener
        swipe_refresh_layout.setOnRefreshListener {
            presenter?.networkcall()
            swipe_refresh_layout.isRefreshing = true
        }
    }

    fun initialize() {
        presenter = MainPresenter(this)

    }

    override fun updateViewData() {
        tv_currentcases?.setText(presenter?.showTotalConfirmed())
    tv_recoveredcases?.setText(presenter?.showTotalRecovered())
    tv_deaths?.setText(presenter?.showTotalDeaths())
    tv_active_cases?.setText(presenter?.showActiveCases())
    tv_closed_cases?.setText(presenter?.showClosedCases())
    llProgressBar?.visibility = View.GONE
    swipe_refresh_layout.isRefreshing = false
}
}