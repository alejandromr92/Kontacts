package com.alejandromr.kontacts.api

sealed class Result<out T>
class Success<T>(val result: T) : Result<T>()
class Failure : Result<Nothing>()
