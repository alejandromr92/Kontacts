package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsListContract {

    interface View : BaseContract.View {

        fun showProgress()

        fun hideProgress()

        fun displayList(list: Set<ContactModel>)

        fun manageEmptyStateVisibility(isEmptyState: Boolean, hasFilteredResults: Boolean)

        fun displayError(retryFromApi: Boolean)

        fun displayErrorWhileDeleting(contact: ContactModel)
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun obtainContacts(fromApi: Boolean)

        fun deleteContact(contact: ContactModel)

        fun filterByInput(input: String)
    }
}
