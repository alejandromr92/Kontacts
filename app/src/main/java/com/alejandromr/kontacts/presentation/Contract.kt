package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.domain.ResultsModel

interface Contract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun displayList(list: ResultsModel)

        fun displayError()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun obtainContacts()
    }
}
