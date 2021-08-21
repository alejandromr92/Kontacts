package com.alejandromr.kontacts.api.model

object ResultsJson {
    val RESULTS_JSON = "{\"results\":[{\"gender\":\"male\",\"name\":{\"title\":\"Ms\",\"first\":\"name\",\"last\":\"surname\"},\"location\":{\"street\":{\"number\":66,\"name\":\"name\"},\"city\":\"city\",\"state\":\"state\",\"country\":\"Netherlands\",\"postcode\":81218,\"coordinates\":{\"latitude\":\"39.9432\",\"longitude\":\"43.9467\"},\"timezone\":{\"offset\":\"+2:00\",\"description\":\"Kaliningrad, South Africa\"}},\"email\":\"mail\",\"registered\":{\"date\":\"date\",\"age\":6},\"phone\":\"phone\",\"picture\":{\"large\":\"large\",\"medium\":\"medium\",\"thumbnail\":\"thumbnail\"}}]}"
    val CONTACT_JSON = "{\"gender\":\"male\",\"name\":{\"title\":\"Ms\",\"first\":\"name\",\"last\":\"surname\"},\"location\":{\"street\":{\"number\":66,\"name\":\"name\"},\"city\":\"city\",\"state\":\"state\",\"country\":\"Netherlands\",\"postcode\":81218,\"coordinates\":{\"latitude\":\"39.9432\",\"longitude\":\"43.9467\"},\"timezone\":{\"offset\":\"+2:00\",\"description\":\"Kaliningrad, South Africa\"}},\"email\":\"mail\",\"registered\":{\"date\":\"date\",\"age\":6},\"phone\":\"phone\",\"picture\":{\"large\":\"large\",\"medium\":\"medium\",\"thumbnail\":\"thumbnail\"}}"
    val LOCATION_JSON = "{\"street\":{\"name\":\"name\",\"number\":66},\"city\":\"city\",\"state\":\"state\",\"country\":\"Netherlands\",\"postcode\":81218,\"coordinates\":{\"latitude\":\"39.9432\",\"longitude\":\"43.9467\"},\"timezone\":{\"offset\":\"+2:00\",\"description\":\"Kaliningrad, South Africa\"}}"
    val NAME_JSON = "{\"title\":\"Ms\",\"first\":\"name\",\"last\":\"surname\"}"
    val PICTURE_JSON = "{\"large\":\"large\",\"medium\":\"medium\",\"thumbnail\":\"thumbnail\"}"
    val REGISTRATION_JSON = "{\"date\":\"date\",\"age\":6}"
    val STREET_JSON = "{\"name\":\"name\",\"number\":\"66\"}"
}
