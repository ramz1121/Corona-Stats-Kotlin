package com.corona.java.coronastatskotlin

import com.corona.java.coronastatskotlin.api.JobAPI
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test

class CaseReposTest {
    @Test
    fun `can get cases method`() {
        // call the api
        val api = JobAPI.getClient("https://pomber.github.io/covid19/")
        //val response = api.getPaymentMethod("authToken").execute()
        // verify the response is OK
        //  assertThat(response.code()).isEqualTo(200)
    }
}