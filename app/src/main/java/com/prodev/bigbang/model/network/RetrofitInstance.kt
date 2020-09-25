package com.prodev.bigbang.model.network

import com.prodev.bigbang.model.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {


        private var INSTANCE: Retrofit? = null
        fun getInstance(): Retrofit {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return INSTANCE!!
            }
        }


    }
}
