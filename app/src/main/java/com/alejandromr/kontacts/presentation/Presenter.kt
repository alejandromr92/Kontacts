package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.api.Success
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

    override fun obtainContacts() {
        coroutineScope.launch {
            view?.showProgress()
            val resultList = getContactsUseCase()
            if (resultList is Success) {
                view?.displayList(resultList.result)
            } else {
                view?.displayError()
            }
            view?.hideProgress()
        }
    }

    override fun detachView() {
        super.detachView()
        coroutineScope.cancel()
    }

}
