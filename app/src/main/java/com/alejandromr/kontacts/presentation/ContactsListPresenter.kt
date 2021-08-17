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
            view?.displayError()
        }
    private val job = SupervisorJob()
    private var coroutineScope =
        CoroutineScope(Dispatchers.Main + job + errorHandler)

    private var contactsList = mutableSetOf<ContactModel>()

    private var deletedContactsList = mutableSetOf<ContactModel>()

    override fun obtainContacts() {
        coroutineScope.launch {
            view?.showProgress()
            val resultList = getContactsUseCase()
            if (resultList is Success) {
                contactsList.addAll(resultList.result.results.filter {
                    !deletedContactsList.contains(
                        it
                    )
                })
                view?.displayList(contactsList)
            } else {
                view?.displayError()
            }
            view?.hideProgress()
        }
    }

    override fun deleteContact(contact: ContactModel) {
        coroutineScope.launch {
            try {
                contact.deleted = true
                deleteContactUseCase(contact)
                contactsList.remove(contact)
                deletedContactsList.add(contact)
                view?.displayList(contactsList)
            } catch (ex: Exception) {
                view?.displayErrorWhileDeleting(contact)
            }
        }
    }

    override fun filterByInput(input: String) {
        view?.displayList(contactsList.filter {
            it.name.first.startsWith(input) || it.name.last.startsWith(
                input
            ) || it.email.startsWith(input)
        }.toSet())
    }

    override fun detachView() {
        super.detachView()
        coroutineScope.cancel()
    }

}
