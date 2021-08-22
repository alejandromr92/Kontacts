package com.alejandromr.kontacts.presentation.ui

import android.widget.EditText
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.adevinta.android.barista.assertion.BaristaListAssertions
import com.alejandromr.kontacts.R
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import com.alejandromr.kontacts.domain.model.StreetModel
import com.alejandromr.kontacts.presentation.ContactViewHolder
import com.alejandromr.kontacts.presentation.ContactsListContract
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.verify
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

class ContactsListFragmentTest {

    @MockK
    private lateinit var presenter: ContactsListContract.Presenter

    @MockK
    private lateinit var navController: NavController

    private val module by lazy {
        module { single(override = true) { presenter } }
    }

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Before
    fun setup() {
        loadKoinModules(module)
    }

    @After
    fun tearDown() {
        unloadKoinModules(module)
    }

    @Test
    fun verifyPresenterInitialized() {
        launchFragment().onFragment { fragment ->
            verify(exactly = 1) {
                presenter.apply {
                    attachView(fragment)
                    obtainContacts(false)
                }
            }
        }
    }

    @Test
    fun verifyPresenterDisplayList() {
        every { presenter.obtainContacts(false) } just Runs

        val nameModel = NameModel("name", "surname")
        val streetModel = StreetModel("name", "66")
        val locationModel = LocationModel(streetModel, "city", "state")
        val pictureModel = PictureModel("large", "medium", "thumbnail")
        val registrationModel = RegistrationModel("date")
        val contactModel = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "mail",
            pictureModel
        )

        val contactsList = setOf(contactModel)

        launchFragment().onFragment { fragment ->
            fragment.displayList(contactsList)
        }

        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.modelList,
            0,
            R.id.name,
            "name surname"
        )

        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.modelList,
            0,
            R.id.email,
            "mail"
        )

        BaristaListAssertions.assertDisplayedAtPosition(
            R.id.modelList,
            0,
            R.id.phone,
            "phone"
        )
    }

    @Test
    fun verifyDisplayEmptyState() {
        launchFragment().onFragment { fragment ->
            fragment.manageEmptyStateVisibility(isEmptyState = true, hasFilteredResults = false)
        }

        onView(withId(R.id.emptyStateMessage)).check(matches(isDisplayed()))
        onView(withId(R.id.emptyStateMessage)).check(
            matches(
                withText(
                    "You currently do not have any contacts,\n" +
                            " tap to load some!"
                )
            )
        )
        onView(withId(R.id.emptyStateMessage)).perform(click())

        verify { presenter.obtainContacts(true) }
    }

    @Test
    fun verifyDisplayError() {
        launchFragment().onFragment { fragment ->
            fragment.displayError(false)
        }

        onView(withText("Something went wrong")).check(matches(isDisplayed()))
        onView(withText("Aceptar")).perform(click())
        verify { presenter.obtainContacts(false) }
    }


    @Test
    fun verifyDisplayErrorWhileDeleting() {
        launchFragment().onFragment { fragment ->
            fragment.displayError(false)
        }

        onView(withText("Would you like to try again?")).check(matches(isDisplayed()))
    }

    @Test
    fun verifyDisplayFilteredContactsEmpty() {
        launchFragment().onFragment { fragment ->
            fragment.manageEmptyStateVisibility(isEmptyState = true, hasFilteredResults = true)
        }

        onView(withId(R.id.emptyStateMessage)).check(matches(isDisplayed()))
        onView(withId(R.id.emptyStateMessage)).check(matches(withText("Your search did not match any contact on your agenda, try changing your search query")))
    }

    @Test
    fun verifyInputOnSearchFiltersList() {
        every { presenter.filterByInput(any()) } just Runs

        val nameModel = NameModel("name", "surname")
        val streetModel = StreetModel("name", "66")
        val locationModel = LocationModel(streetModel, "city", "state")
        val pictureModel = PictureModel("large", "medium", "thumbnail")
        val registrationModel = RegistrationModel("date")
        val contactModel = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "mail",
            pictureModel
        )
        val contactModel2 = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "email2",
            pictureModel
        )

        val contactsList = setOf(contactModel, contactModel2)

        launchFragment().onFragment { fragment ->
            fragment.displayList(contactsList)
        }

        onView(withId(R.id.searchView)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("ema"))

        verify { presenter.filterByInput("e") }
        verify { presenter.filterByInput("em") }
        verify { presenter.filterByInput("ema") }
    }

    @Test
    fun verifyDeleteOnItemSwipeLeft() {
        val nameModel = NameModel("name", "surname")
        val streetModel = StreetModel("name", "66")
        val locationModel = LocationModel(streetModel, "city", "state")
        val pictureModel = PictureModel("large", "medium", "thumbnail")
        val registrationModel = RegistrationModel("date")
        val contactModel = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "mail",
            pictureModel
        )
        val contactModel2 = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "email2",
            pictureModel
        )

        val contactsList = setOf(contactModel, contactModel2)

        launchFragment().onFragment { fragment ->
            fragment.displayList(contactsList)
        }

        onView(withId(R.id.modelList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ContactViewHolder>(
                0,
                swipeLeft()
            )
        )

        verify { presenter.deleteContact(contactModel) }
    }

    @Test
    fun verifyNavigationToDetail() {
        val nameModel = NameModel("name", "surname")
        val streetModel = StreetModel("name", "66")
        val locationModel = LocationModel(streetModel, "city", "state")
        val pictureModel = PictureModel("large", "medium", "thumbnail")
        val registrationModel = RegistrationModel("date")
        val contactModel = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "mail",
            pictureModel
        )
        val contactModel2 = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "email2",
            pictureModel
        )

        val contactsList = setOf(contactModel, contactModel2)

        launchFragment().onFragment { fragment ->
            fragment.displayList(contactsList)
        }

        onView(withId(R.id.modelList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ContactViewHolder>(
                0,
                click()
            )
        )

        verify { navController.navigate(ContactsListFragmentDirections.navigateToDetail(contactModel)) }
    }

    @Test
    fun checkProgressShows() {
        launchFragment().onFragment { fragment ->
            fragment.showProgress()
        }

        onView(withId(R.id.listProgressBar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkProgressHides() {
        launchFragment().onFragment { fragment ->
            fragment.hideProgress()
        }

        onView(withId(R.id.listProgressBar))
            .check(matches(Matchers.not(isDisplayed())))
    }

    private fun launchFragment(): FragmentScenario<ContactsListFragment> =
        launchFragmentInContainer(themeResId = R.style.Theme_Kontacts) {
            ContactsListFragment().also { fragment ->

                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navController.setGraph(R.navigation.contacts_nav_graph)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
}
