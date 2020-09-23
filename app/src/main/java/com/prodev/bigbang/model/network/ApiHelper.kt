package com.prodev.bigbang.model.network

class ApiHelper(private val apiService: BeerService) {

    suspend fun getBeers() = apiService.getAllBeer()
}