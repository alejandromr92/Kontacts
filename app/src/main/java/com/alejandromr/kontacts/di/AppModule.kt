package com.alejandromr.kontacts.di

import org.koin.core.module.Module

val appModules = mutableListOf<Module>().apply {
    add(presentersModule)
    add(domainModule)
    add(repositoryModule)
    add(diskModule)
    add(networkModule)
    add(mappersModule)
}.toList()
