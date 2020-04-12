package com.corona.java.coronastatskotlin.util

import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices

object Constant {

    const val BASE_URL = "https://pomber.github.io/covid19/"
    const val CASES = "timeseries.json"
   /* val retrofitService: JobServices
        get() = JobAPI.getClient(BASE_URL)
            .create(JobServices::class.java)
*/
}