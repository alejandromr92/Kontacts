package com.alejandromr.kontacts.domain

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val computation: CoroutineDispatcher

    val unconfined: CoroutineDispatcher
}
