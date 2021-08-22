package com.alejandromr.kontacts.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ContactDbModel::class],
    version = 1
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao
}
