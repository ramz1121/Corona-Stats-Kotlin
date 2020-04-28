package com.corona.java.coronastatskotlin

import com.corona.java.coronastatskotlin.api.JobAPI
import com.corona.java.coronastatskotlin.api.JobServices
import com.google.gson.JsonObject
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class JobAPITest {
    @Test
    open fun getRetrofit_Instance_Test() {
        Assert.assertNotNull(
            "Retrofit client instance is null",
            JobAPI.getClient("https://pomber.github.io/covid19/")
        )
    }
    @Test
    open fun mockTest(){
        val mockWebServer = MockWebServer()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://pomber.github.io/covid19/").toString())
            .addConverterFactory(GsonConverterFactory.create())//TODO Add your Retrofit parameters here
            .build()

        //Set a response for retrofit to handle. You can copy a sample
        //response from your server to simulate a correct result or an error.
        //MockResponse can also be customized with different parameters
        //to match your test needs

        //Set a response for retrofit to handle. You can copy a sample
        //response from your server to simulate a correct result or an error.
        //MockResponse can also be customized with different parameters
        //to match your test needs
        mockWebServer.enqueue(MockResponse().setBody("{\n" +
                "  \"Afghanistan\": [\n" +
                "    {\n" +
                "      \"date\": \"2020-1-22\",\n" +
                "      \"confirmed\": 0,\n" +
                "      \"deaths\": 0,\n" +
                "      \"recovered\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"date\": \"2020-1-23\",\n" +
                "      \"confirmed\": 0,\n" +
                "      \"deaths\": 0,\n" +
                "      \"recovered\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"date\": \"2020-1-24\",\n" +
                "      \"confirmed\": 0,\n" +
                "      \"deaths\": 0,\n" +
                "      \"recovered\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"date\": \"2020-1-25\",\n" +
                "      \"confirmed\": 0,\n" +
                "      \"deaths\": 0,\n" +
                "      \"recovered\": 0\n" +
                "    }]}"))

        val service: JobServices =
            retrofit.create(JobServices::class.java)

        //With your service created you can now call its method that should
        //consume the MockResponse above. You can then use the desired
        //assertion to check if the result is as expected. For example:

        //With your service created you can now call its method that should
        //consume the MockResponse above. You can then use the desired
        //assertion to check if the result is as expected. For example:
        val call: Call<JsonObject>? = service.getJSON()
        Assert.assertTrue(call?.execute() != null)

        //Finish web server

        //Finish web server
        mockWebServer.shutdown()
    }
}