package com.corona.java.coronastatskotlin.api

import com.corona.java.coronastatskotlin.util.Constant
import com.google.gson.JsonObject
import com.squareup.okhttp.ResponseBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import java.util.*


interface JobServices {
    @GET(Constant.CASES)
    //open fun getFlowers(): Call<JsonElement?>?
    fun getJSON(): Call<JsonObject>?

}