package com.alejandromr.kontacts.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveRetrievedContacts(contacts: List<ContactDbModel>)

    @Query("SELECT * FROM contacts WHERE deleted = 0")
    suspend fun retrieveContacts(): List<ContactDbModel>

    @Update
    suspend fun deleteContact(contactModel: ContactDbModel)
}
