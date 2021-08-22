package com.alejandromr.kontacts.presentation

import com.alejandromr.kontacts.api.Failure
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.usecase.DeleteContactUseCase
import com.alejandromr.kontacts.domain.usecase.GetContactsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ContactsListPresenterTest {

    @MockK
    private lateinit var view: ContactsListContract.View

    @MockK
    private lateinit var getContactsUseCase: GetContactsUseCase

    @MockK
    private lateinit var deleteContactUseCase: DeleteContactUseCase

    @MockK
    private lateinit var contactModel: ContactModel

    private val presenter by lazy {
        ContactsListPresenter(getContactsUseCase, deleteContactUseCase)
    }

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        presenter.attachView(view)
    }

    @After
    fun shutDown() {
        Dispatchers.resetMain()
        presenter.detachView()
    }

    @Test
    fun `obtain contacts success flow`() {
        coEvery { getContactsUseCase(false) } returns Success(setOf(contactModel))

        presenter.obtainContacts(false)

        coVerifySequence {
            view.showProgress()
            getContactsUseCase(false)
            view.displayList(any())
            view.manageEmptyStateVisibility(isEmptyState = false, hasFilteredResults = false)
            view.hideProgress()
        }
    }

    @Test
    fun `obtain contacts failure flow`() {
        coEvery { getContactsUseCase(false) } returns Failure()

        presenter.obtainContacts(false)

        coVerifySequence {
            view.showProgress()
            getContactsUseCase(false)
            view.displayError(false)
            view.manageEmptyStateVisibility(isEmptyState = true, hasFilteredResults = false)
            view.hideProgress()
        }
    }

    @Test
    fun `delete contact success flow`() {
        coEvery { deleteContactUseCase(contactModel) } returns Unit

        presenter.deleteContact(contactModel)

        coVerifySequence {
            view.showProgress()
            deleteContactUseCase(contactModel)
            view.manageEmptyStateVisibility(isEmptyState = true, hasFilteredResults = false)
            view.displayList(any())
            view.hideProgress()
        }
    }

    @Test
    fun `delete contact failure flow`() {
        coEvery { deleteContactUseCase(contactModel) } throws Exception()

        presenter.deleteContact(contactModel)

        coVerifySequence {
            view.showProgress()
            deleteContactUseCase(contactModel)
            view.displayErrorWhileDeleting(contactModel)
            view.hideProgress()
        }
    }

    @Test
    fun `filter empty input flow`() {
        presenter.filterByInput("")

        verifySequence {
            view.manageEmptyStateVisibility(isEmptyState = true, false)
            view.displayList(any())
        }
    }

    @Test
    fun `filter non empty input flow`() {
        coEvery { getContactsUseCase(false) } returns Success(setOf(contactModel))
        every { contactModel.email } returns "input@mail.com"

        presenter.obtainContacts(false)
        presenter.filterByInput("input")

        coVerifySequence {
            // Obtain contacts success sequence, necessary for the filter to work
            view.showProgress()
            getContactsUseCase(false)
            view.displayList(any())
            view.manageEmptyStateVisibility(isEmptyState = false, hasFilteredResults = false)
            view.hideProgress()

            view.manageEmptyStateVisibility(isEmptyState = false, true)
            view.displayList(any())
        }
    }
}
