package com.alejandromr.kontacts.mappers

interface Mapper<in FROM, out TO> {

    fun map(from: FROM?): TO
}
