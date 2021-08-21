package com.alejandromr.kontacts.datasource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

object DatabaseTestHelper {
    // Workaround function to avoid IllegalStateException: This job has not completed yet. Caused
    // when testing suspend room functions.
    fun testBlocking(test: suspend CoroutineScope.() -> Unit) = runBlocking { test() }
}
