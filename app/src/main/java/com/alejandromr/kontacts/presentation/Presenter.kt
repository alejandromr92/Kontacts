package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.GetContactsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class Presenter(private val getContactsUseCase: GetContactsUseCase) : Contract.Presenter {

    override var view: Contract.View? = null

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

    override fun navigateToContactDetail(contact: ContactModel) {

    }

    override fun deleteContact(contact: ContactModel) {
        this.contactsList.remove(contact)
        this.deletedContactsList.add(contact)
        view?.displayList(contactsList)
    }

    override fun detachView() {
        super.detachView()
        coroutineScope.cancel()
    }

}
