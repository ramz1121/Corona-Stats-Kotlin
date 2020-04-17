package com.corona.java.coronastatskotlin.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.corona.java.coronastatskotlin.R
import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), CaseNumbersInteractor.mainView {
    private var presenter: MainPresenter? = null
    private var tv_currentcases: TextView? = null
    private var tv_recovered: TextView? = null
    private var tv_deaths: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        presenter?.networkcall()
    }
    fun initialize() {
        presenter = MainPresenter(this)
        tv_currentcases = findViewById(R.id.tv_currentcases)
        tv_recovered = findViewById(R.id.tv_recoveredcases)
        tv_deaths = findViewById(R.id.tv_deaths)
    }

    override fun updateViewData() {
        tv_currentcases?.setText(presenter?.showTotalConfirmed())
        tv_recovered?.setText(presenter?.showTotalRecovered())
        tv_deaths?.setText(presenter?.showTotalDeaths())
    }
}