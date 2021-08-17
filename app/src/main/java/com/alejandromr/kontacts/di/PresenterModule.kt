package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.presentation.ContactsListContract
import com.alejandromr.kontacts.presentation.ContactsListPresenter
import org.koin.dsl.module

val presentersModule = module {

    factory<ContactsListContract.Presenter> { ContactsListPresenter(get(), get()) }
}
