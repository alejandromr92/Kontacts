package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.domain.ContactsRepository
import com.alejandromr.kontacts.repository.ContactsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
}
