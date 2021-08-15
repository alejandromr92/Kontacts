package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.mappers.ContactMapper
import com.alejandromr.kontacts.mappers.LocationMapper
import com.alejandromr.kontacts.mappers.NameMapper
import com.alejandromr.kontacts.mappers.PictureMapper
import com.alejandromr.kontacts.mappers.RegistrationMapper
import com.alejandromr.kontacts.mappers.ResultMapper
import com.alejandromr.kontacts.mappers.StreetMapper
import org.koin.dsl.module

val mappersModule = module {
    single { StreetMapper() }
    single { NameMapper() }
    single { LocationMapper(get()) }
    single { RegistrationMapper() }
    single { PictureMapper() }
    single { ContactMapper(get(), get(), get(), get()) }
    single { ResultMapper(get()) }
}
