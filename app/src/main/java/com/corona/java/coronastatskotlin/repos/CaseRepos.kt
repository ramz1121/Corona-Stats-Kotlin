package com.corona.java.coronastatskotlin.repos

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.main.CaseNumbersInteractor
import com.corona.java.coronastatskotlin.util.Common
import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.squareup.okhttp.ResponseBody
import okhttp3.RequestBody
import okhttp3.internal.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class CaseRepos : CaseNumbersInteractor.casesModel {
    private var apiclient: JobServices? = null
    private var confirmedf = ""
    private var recoveredf = ""
    private var deathsf = ""
    override fun getTotalConfirmed() = confirmedf
    override fun getTotalDeaths() = deathsf
    override fun getTotalRecovered() = recoveredf


    init {
        apiclient = Constant.retrofitService
    }

    override fun getTotalCases(presenter: CaseNumbersInteractor.mainPresenter) {
        try {
            apiclient?.getJSON()
                ?.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {

                        var confirmed: Double = 0.0
                        var recovered: Double = 0.0
                        var deaths: Double = 0.0
                        val jsonResponse = response.body()
                        val set = jsonResponse?.entrySet()
                        val it = set?.iterator()
                        while (it!!.hasNext()) {
                            val item = it.next()
                            val country = item.key
                            val countryData = item.value
                            val last =
                                countryData.asJsonArray.get((countryData as JsonArray).size() - 1)
                            print(last)
                            confirmed = confirmed + last.asJsonObject["confirmed"].asDouble
                            recovered = recovered + last.asJsonObject["recovered"].asDouble
                            deaths = deaths + last.asJsonObject["deaths"].asDouble

                        }

                        confirmedf = Common.formatNumber(confirmed)
                        deathsf = Common.formatNumber(deaths)
                        recoveredf = Common.formatNumber(recovered)
                        presenter.uiAutoUpdate()
                    }

                    override fun onFailure(
                        call: Call<JsonObject>,
                        t: Throwable
                    ) {
                    }
                })
        } catch (e: Exception) {
            println(e.message)
        }


    }

}