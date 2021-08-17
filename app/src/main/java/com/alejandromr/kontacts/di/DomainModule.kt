package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.domain.AppDispatchers
import com.alejandromr.kontacts.domain.usecase.DeleteContactUseCase
import com.alejandromr.kontacts.domain.usecase.GetContactsUseCase
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import org.koin.dsl.module

val domainModule = module {
    factory { GetContactsUseCase(get(), get()) }
    factory { DeleteContactUseCase(get(), get()) }

    single<AppDispatchers> {
        object : AppDispatchers {
            override val main: CoroutineDispatcher = Dispatchers.Main
            override val io: CoroutineDispatcher = Schedulers.io().asCoroutineDispatcher()
            override val computation: CoroutineDispatcher =
                Schedulers.computation().asCoroutineDispatcher()
            override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
        }
    }
}
