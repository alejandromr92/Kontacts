package com.alejandromr.kontacts.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alejandromr.kontacts.domain.ContactModel

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveRetrievedContacts(contacts: Set<ContactModel>)

    @Query("SELECT * FROM contacts WHERE deleted = 0")
    suspend fun retrieveContacts(): Set<ContactModel>

    @Update
    suspend fun deleteContact(contactModel: ContactModel)
}
