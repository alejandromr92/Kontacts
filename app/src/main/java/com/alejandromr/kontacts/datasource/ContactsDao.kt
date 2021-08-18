package com.alejandromr.kontacts.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveRetrievedContacts(contacts: List<ContactDbModel>)

    @Query("SELECT * FROM contacts WHERE deleted = 0")
    suspend fun retrieveContacts(): List<ContactDbModel>

    @Query("UPDATE contacts SET deleted = 1 WHERE email = :email")
    suspend fun deleteContact(email: String)
}
