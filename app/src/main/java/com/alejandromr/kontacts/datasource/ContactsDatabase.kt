package com.alejandromr.kontacts.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandromr.kontacts.domain.ContactModel

@Database(
    entities = [ContactModel::class],
    version = 1
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao
}
