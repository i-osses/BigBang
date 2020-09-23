package com.prodev.bigbang.model.network

import com.prodev.bigbang.model.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private fun getRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService: BeerService = getRetrofit().create(BeerService::class.java)
    }
}
