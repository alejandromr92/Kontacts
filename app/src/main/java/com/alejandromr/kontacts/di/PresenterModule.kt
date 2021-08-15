package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.presentation.Contract
import com.alejandromr.kontacts.presentation.Presenter
import org.koin.dsl.module

val presentersModule = module {

    factory<Contract.Presenter> { Presenter(get()) }
}
