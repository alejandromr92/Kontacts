package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsListContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun displayList(list: Set<ContactModel>)

        fun displayError()

        fun displayErrorWhileDeleting(contact: ContactModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun obtainContacts()

        fun deleteContact(contact: ContactModel)

        fun filterByInput(input: String)
    }
}
