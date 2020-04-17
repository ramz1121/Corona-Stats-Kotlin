package com.corona.java.coronastatskotlin.main

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.corona.java.coronastatskotlin.R
import com.corona.java.coronastatskotlin.util.Common
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        llProgressBar?.visibility = View.GONE
        swipe_refresh_layout.isRefreshing = false
    }
}