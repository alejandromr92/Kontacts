package com.alejandromr.kontacts.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.alejandromr.kontacts.domain.model.ContactModel

@Database(
    entities = [ContactDbModel::class],
    version = 1
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao
}
