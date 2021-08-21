package com.alejandromr.kontacts.datasource

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class ContactsDaoTest {

    private lateinit var database: ContactsDatabase
    private lateinit var contactsDao: ContactsDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ContactsDatabase::class.java
        ).build()
        contactsDao = database.contactsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `get contact from empty database`() {
        DatabaseTestHelper.testBlocking {
            val result = contactsDao.retrieveContacts()

            assertEquals(listOf<ContactDbModel>(), result)
        }
    }


    @Test
    fun `add contacts to database and check the result is in the database`() {
        DatabaseTestHelper.testBlocking {
            val contactsList = ContactDatabaseMock.getDbContacts()

            contactsDao.saveRetrievedContacts(contactsList)

            val result = contactsDao.retrieveContacts()

            assertEquals(contactsList[0].name, result[0].name)
            assertEquals(contactsList[0].surname, result[0].surname)
            assertEquals(contactsList[0].gender, result[0].gender)
            assertEquals(contactsList[0].streetName, result[0].streetName)
            assertEquals(contactsList[0].streetNumber, result[0].streetNumber)
            assertEquals(contactsList[0].city, result[0].city)
            assertEquals(contactsList[0].state, result[0].state)
            assertEquals(contactsList[0].registeredDate, result[0].registeredDate)
            assertEquals(contactsList[0].phone, result[0].phone)
            assertEquals(contactsList[0].email, result[0].email)
            assertEquals(contactsList[0].pictureThumbnail, result[0].pictureThumbnail)
            assertEquals(contactsList[0].pictureLarge, result[0].pictureLarge)
            assertEquals(contactsList[0].pictureMedium, result[0].pictureMedium)
            assertEquals(contactsList[0].deleted, result[0].deleted)

            assertEquals(contactsList[1].name, result[1].name)
            assertEquals(contactsList[1].surname, result[1].surname)
            assertEquals(contactsList[1].gender, result[1].gender)
            assertEquals(contactsList[1].streetName, result[1].streetName)
            assertEquals(contactsList[1].streetNumber, result[1].streetNumber)
            assertEquals(contactsList[1].city, result[1].city)
            assertEquals(contactsList[1].state, result[1].state)
            assertEquals(contactsList[1].registeredDate, result[1].registeredDate)
            assertEquals(contactsList[1].phone, result[1].phone)
            assertEquals(contactsList[1].email, result[1].email)
            assertEquals(contactsList[1].pictureThumbnail, result[1].pictureThumbnail)
            assertEquals(contactsList[1].pictureLarge, result[1].pictureLarge)
            assertEquals(contactsList[1].pictureMedium, result[1].pictureMedium)
            assertEquals(contactsList[1].deleted, result[1].deleted)
        }
    }

    @Test
    fun `add contacts to database, call delete method and check the deleted contact it is not in the retrieved list`() {
        DatabaseTestHelper.testBlocking {
            val contactsList = ContactDatabaseMock.getDbContacts()

            contactsDao.saveRetrievedContacts(contactsList)
            val result = contactsDao.retrieveContacts()

            assertEquals(2, result.size)

            contactsDao.deleteContact("email")

            val deleteResult = contactsDao.retrieveContacts()

            assertEquals(1, deleteResult.size)
        }
    }

    @Test
    fun `add two equal contacts to database, check that only one has been added and the second one is ignored`() {
        DatabaseTestHelper.testBlocking {
            val contactsList = ContactDatabaseMock.getDuplicatedDbContacts()

            contactsDao.saveRetrievedContacts(contactsList)
            val result = contactsDao.retrieveContacts()

            assertEquals(1, result.size)
        }
    }
}
