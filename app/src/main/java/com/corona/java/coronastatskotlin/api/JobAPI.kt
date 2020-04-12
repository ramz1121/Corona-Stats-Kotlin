package com.corona.java.coronastatskotlin.api

import com.corona.java.coronastatskotlin.util.Common
import com.corona.java.coronastatskotlin.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JobAPI {
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

}