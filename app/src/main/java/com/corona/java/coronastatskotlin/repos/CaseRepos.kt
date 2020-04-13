package com.corona.java.coronastatskotlin.repos

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.main.CaseNumbersInteractor
import com.corona.java.coronastatskotlin.util.Constant
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
       // apiclient = JobAPI.getAPI()
    }

    override fun getTotalCases(presenter: CaseNumbersInteractor.mainPresenter) {
    /*    try {
            //  Toast.makeText(this.con, "test", Toast.LENGTH_SHORT)

            //if (Common.isInternetConnected(Objects.requireNonNull(context))) {
            apiclient?.getJSON()
                ?.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        val jsonResponse = response.body()
                        print("hi")
                        *//* val entrySet: Set<*> = jsonResponse.entries
                     val it = entrySet.iterator()
                     var confirmed = 0.0
                     var deaths = 0.0
                     var recovered = 0.0
                     while (it.hasNext()) {
                         val me =
                             it.next() as Map.Entry<*, *>
                         val Country = me.key.toString()
                         val list = me.value as ArrayList<*>
                         val last =
                             list[list.size - 1] as LinkedTreeMap<*, *>
                         confirmed = confirmed + last["confirmed"] as Double
                         recovered = recovered + last["recovered"] as Double
                         deaths = deaths + last["deaths"] as Double
                     }
                     confirmedf = confirmed.toString();
                     deathsf = deaths.toString()
                     recoveredf = recovered.toString()
                     presenter.uiAutoUpdate()*//*
                    }

                    override fun onFailure(
                        call: Call<ResponseBody>,
                        t: Throwable
                    ) {
                    }
                })
        } catch (e: Exception) {
            println(e.message)
        }
*/
    }

}