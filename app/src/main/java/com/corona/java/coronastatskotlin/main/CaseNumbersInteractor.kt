package com.corona.java.coronastatskotlin.main


interface CaseNumbersInteractor {

    interface casesModel {
        fun getTotalCases(presenter: mainPresenter)
        fun getTotalDeaths():String
        fun getTotalConfirmed():String
        fun getTotalRecovered():String
        fun getActiveCases():String
        fun getClosedCases():String
        fun getArrayOfTotalCases(): MutableList<Float>
        fun getArrayOfDates():MutableList<String>

    }
    interface mainView {
        fun updateViewData()
    }

    interface mainPresenter {
        fun uiAutoUpdate()
        fun networkcall()
        fun showTotalDeaths():String
        fun showTotalConfirmed():String
        fun showTotalRecovered():String
        fun showActiveCases():String
        fun showClosedCases():String
        fun showArrayOfTotalCases(): MutableList<Float>
        fun showArrayOfDates():MutableList<String>
    }
}