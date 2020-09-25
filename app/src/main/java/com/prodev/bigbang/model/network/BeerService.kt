package com.prodev.bigbang.model.network

import com.prodev.bigbang.model.Beer
import retrofit2.Call
import retrofit2.http.GET

interface BeerService {

    @GET("/v2/beers")
    fun getAllBeer(): Call<List<Beer>>
}