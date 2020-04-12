package com.corona.java.coronastatskotlin.main

import com.corona.java.coronastatskotlin.repos.CaseRepos

class MainPresenter(mainView: CaseNumbersInteractor.mainView) :
    CaseNumbersInteractor.mainPresenter {
    private var view: CaseNumbersInteractor.mainView = mainView
    private var model: CaseNumbersInteractor.casesModel = CaseRepos()

    override fun uiAutoUpdate() {
        view.updateViewData()
    }

    override fun networkcall() {
        model?.getTotalCases(this)
    }

    override fun showTotalDeaths(): String {
        TODO("Not yet implemented")
        model.getTotalDeaths()
    }

    override fun showTotalConfirmed(): String {
        TODO("Not yet implemented")
        model.getTotalConfirmed()
    }

    override fun showTotalRecovered(): String {
        TODO("Not yet implemented")
        model.getTotalRecovered()
    }


}