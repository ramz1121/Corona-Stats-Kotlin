package com.corona.java.coronastatskotlin.api

import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonElement
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import java.util.*
import retrofit2.http.GET


interface JobServices {
    @GET("timeseries.json")
    fun getJSON(): Call<String>
    //open fun getFlowers(): Call<JsonElement?>?

}