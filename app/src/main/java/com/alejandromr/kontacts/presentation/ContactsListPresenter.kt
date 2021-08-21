package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.usecase.DeleteContactUseCase
import com.alejandromr.kontacts.domain.usecase.GetContactsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ContactsListPresenter(
    private val getContactsUseCase: GetContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) :
    ContactsListContract.Presenter {

    override var view: ContactsListContract.View? = null

    private val errorHandler =
        CoroutineExceptionHandler { _, _ ->
            view?.hideProgress()
            view?.displayError(false)
        }
    private val job = SupervisorJob()
    private var coroutineScope =
        CoroutineScope(Dispatchers.Main + job + errorHandler)

    private var contactsList = mutableSetOf<ContactModel>()
    private var deletedContactsList = mutableSetOf<ContactModel>()

    override fun obtainContacts(fromApi: Boolean) {
        coroutineScope.launch {
            view?.showProgress()
            val resultList = getContactsUseCase(fromApi)
            if (resultList is Success) {
                contactsList.addAll(resultList.result.filter { !deletedContactsList.contains(it) })
                view?.displayList(contactsList)
            } else {
                view?.displayError(fromApi)
            }
            view?.manageEmptyStateVisibility(contactsList.isEmpty(), false)
            view?.hideProgress()
        }
    }

    override fun deleteContact(contact: ContactModel) {
        coroutineScope.launch {
            try {
                contactsList.remove(contact)
                deletedContactsList.add(contact)
                contact.deleted = true
                deleteContactUseCase(contact)
                view?.manageEmptyStateVisibility(contactsList.isEmpty(), false)
                view?.displayList(contactsList)
            } catch (ex: Exception) {
                view?.displayErrorWhileDeleting(contact)
            }
        }
    }

    override fun filterByInput(input: String) {
        val filteredContacts = contactsList.filter {
            it.name.first.startsWith(input) || it.name.last.startsWith(
                input
            ) || it.email.startsWith(input)
        }.toSet()

        view?.manageEmptyStateVisibility(
            contactsList.isEmpty(),
            filteredContacts.isEmpty() && input.isNotEmpty()
        )
        view?.displayList(filteredContacts)
    }

    override fun detachView() {
        super.detachView()
        coroutineScope.cancel()
    }

}
