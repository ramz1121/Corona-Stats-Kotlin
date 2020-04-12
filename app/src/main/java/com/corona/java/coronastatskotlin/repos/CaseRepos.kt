package com.corona.java.coronastatskotlin.repos

import android.content.Context
import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.main.CaseNumbersInteractor
import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonElement
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CaseRepos : CaseNumbersInteractor.casesModel {
 //   private var apiclient: JobServices? = null
    private var confirmedf = ""
    private var recoveredf = ""
    private var deathsf = ""
    override fun getTotalConfirmed() = confirmedf
    override fun getTotalDeaths() = deathsf
    override fun getTotalRecovered() = recoveredf
    var context:Context?=null


    private var apiclient: JobServices? = null

    init {
        apiclient = JobAPI.client.create(JobServices::class.java)
    }
    override fun getTotalCases(presenter: CaseNumbersInteractor.mainPresenter) {
        try {
            println("other message")

                //if (Common.isInternetConnected(Objects.requireNonNull(context))) {
            apiclient?.getJSON()?.enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    println("other message")
                    val jsonResponse = response.body()
                    /* val entrySet: Set<*> = jsonResponse.entries
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
                 presenter.uiAutoUpdate()*/
                }

                override fun onFailure(
                    call: Call<String>,
                    t: Throwable
                ) {
                }
            })
        } catch (e: Exception) {
            println(e.message)
        }

    }

}