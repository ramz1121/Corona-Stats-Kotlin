package com.corona.java.coronastatskotlin.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.corona.java.coronastatskotlin.R
import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices
import com.corona.java.coronastatskotlin.util.Common
import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonObject
import com.squareup.okhttp.ResponseBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity(), CaseNumbersInteractor.mainView {
    private var presenter: MainPresenter? = null

    private var tv_currentcases: TextView? = null
    private var tv_recovered: TextView? = null
    private var tv_deaths: TextView? = null
    private var apiclient: JobServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiclient = Constant.retrofitService
        initialize()
        // presenter?.networkcall()


    }

    fun initialize() {
        presenter = MainPresenter(this)
        tv_currentcases = findViewById(R.id.tv_currentcases)
        tv_recovered = findViewById(R.id.tv_recoveredcases)
        tv_deaths = findViewById(R.id.tv_deaths)

        try {
            //  Toast.makeText(this.con, "test", Toast.LENGTH_SHORT)

            //if (Common.isInternetConnected(Objects.requireNonNull(context))) {
            apiclient?.getJSON()
                ?.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        val jsonResponse = response.body().toString()
                        print(jsonResponse)
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
                        call: Call<JsonObject>,
                        t: Throwable
                    ) {
                    }
                })
        } catch (e: Exception) {
            println(e.message)
        }


    }

    override fun updateViewData() {
        tv_currentcases?.setText(presenter?.showTotalConfirmed())
        tv_recovered?.setText(presenter?.showTotalRecovered())
        tv_deaths?.setText(presenter?.showTotalDeaths())
    }
    /*override fun (confirmed: Double, recovered: Double, deaths: Double) {
         tv_currentcases?.setText(confirmed.toInt())
         tv_recovered?.setText(recovered.toInt())
         tv_deaths?.setText(deaths.toInt())
     }*/
}