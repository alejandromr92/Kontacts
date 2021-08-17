package com.alejandromr.kontacts.di

import androidx.room.Room
import com.alejandromr.kontacts.datasource.ContactsDao
import com.alejandromr.kontacts.datasource.ContactsDataSourceImpl
import com.alejandromr.kontacts.datasource.ContactsDatabase
import com.alejandromr.kontacts.repository.ContactsDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val diskModule = module {
    /** Room instance */
    single<ContactsDatabase> {
        Room.databaseBuilder(androidContext(), ContactsDatabase::class.java, "contacts-db")
            // This fallback is used to avoid crashing the app if a migration function is not found.
            .fallbackToDestructiveMigration()
            .build()
    }

    /** Room DAOs */
    single<ContactsDao> { get<ContactsDatabase>().contactsDao() }

    /** Data Sources */
    single<ContactsDataSource> { ContactsDataSourceImpl(get(), get(), get()) }

}
