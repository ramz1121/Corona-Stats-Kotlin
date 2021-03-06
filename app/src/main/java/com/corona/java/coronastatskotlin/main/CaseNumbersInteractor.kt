package com.corona.java.coronastatskotlin.main


interface CaseNumbersInteractor {

    interface casesModel {
        fun getTotalCases(presenter: mainPresenter)
        fun getTotalDeaths():String
        fun getTotalConfirmed():String
        fun getTotalRecovered():String
        fun getActiveCases():String
        fun getClosedCases():String

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
    }
}