package com.corona.java.coronastatskotlin.main

import java.util.*

data class MainActivtyModel(
    var string: Date, var confirmed: Double = 0.0,
    var deaths: Double = 0.0, var recovered: Double = 0.0
)