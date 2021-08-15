package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.domain.ContactModel

interface Contract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun displayList(list: Set<ContactModel>)

        fun displayError()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun obtainContacts()

        fun navigateToContactDetail(contact: ContactModel)

        fun deleteContact(contact: ContactModel)
    }
}
