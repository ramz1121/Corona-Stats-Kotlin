package com.corona.java.coronastatskotlin.repos

import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.main.CaseNumbersInteractor
import com.corona.java.coronastatskotlin.util.Common
import com.corona.java.coronastatskotlin.util.Constant
import com.github.mikephil.charting.data.Entry
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.FileLock
import java.text.SimpleDateFormat
import java.util.*


class CaseRepos : CaseNumbersInteractor.casesModel {
    private var apiclient: JobServices? = null
    private var confirmedf = ""
    private var recoveredf = ""
    private var deathsf = ""
    private var totalclosedf = ""
    private var totalactivef = ""
    override fun getTotalConfirmed() = confirmedf
    override fun getTotalDeaths() = deathsf
    override fun getTotalRecovered() = recoveredf
    override fun getActiveCases() = totalactivef
    override fun getClosedCases() = totalclosedf
    val dateArray = mutableListOf<String>()
    val caseArray = mutableListOf<Float>()
    override fun getArrayOfTotalCases() = caseArray

    override fun getArrayOfDates() = dateArray

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

                        var confirmed = 0.0
                        var recovered = 0.0
                        var deaths = 0.0
                        var active = 0.0
                        var closed = 0.0
                        val jsonResponse = response.body()
                        val set = jsonResponse?.entrySet()
                        val it = set?.iterator()
                        while (it!!.hasNext()) {
                            val item = it.next()
                            val country = item.key
                            val countryData = item.value
                            val last =
                                countryData.asJsonArray.get((countryData as JsonArray).size() - 1)
                            print("value" + last)
                            confirmed = confirmed + last.asJsonObject["confirmed"].asDouble
                            recovered = recovered + last.asJsonObject["recovered"].asDouble
                            deaths = deaths + last.asJsonObject["deaths"].asDouble

                            //graph data
                            val values = mutableListOf<Entry>()

                            for (n in 0 until countryData.size()) {
                                val item = countryData.asJsonArray.get(n)

                                //  val date = item.asJsonObject["date"].asFloat
                                val parser =
                                    SimpleDateFormat("yyyy-MM-dd")
                                val formatter =
                                    SimpleDateFormat("ddMM")
                                val date: String =
                                    formatter.format(parser.parse(item.asJsonObject["date"].asString))
                                val totalcases = item.asJsonObject["confirmed"].asFloat
                                dateArray.add(date)
                                caseArray.add(totalcases)
                            }

                        }

                        confirmedf = Common.formatNumber(confirmed)
                        deathsf = Common.formatNumber(deaths)
                        recoveredf = Common.formatNumber(recovered)
                        //get total active cases
                        active = confirmed - (deaths + recovered)
                        totalactivef = Common.formatNumber(active)
                        //get total closed cases
                        closed = deaths + recovered
                        totalclosedf = Common.formatNumber(closed)
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