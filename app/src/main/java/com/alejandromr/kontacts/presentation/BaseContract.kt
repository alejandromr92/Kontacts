package com.alejandromr.kontacts.presentation

interface BaseContract {

    interface View {
        fun showProgress()

        fun hideProgress()
    }

    interface Presenter<V : View> {

        var view: V?

        fun attachView(newView: V) {
            view = newView
        }

        fun detachView() {
            view = null
        }
    }
}
