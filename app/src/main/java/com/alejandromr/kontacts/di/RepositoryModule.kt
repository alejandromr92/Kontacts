package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.api.DataSourceImpl
import com.alejandromr.kontacts.domain.Repository
import com.alejandromr.kontacts.repository.DataSource
import com.alejandromr.kontacts.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<Repository> { RepositoryImpl(get()) }
    single<DataSource> { DataSourceImpl(get(), get()) }
}
