package com.prodev.bigbang.model.repository

import androidx.lifecycle.MutableLiveData
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.db.BeerDAO
import com.prodev.bigbang.model.network.RetrofitInstance.Companion.apiService

class BeerRepository(private val dao: BeerDAO) {

    val beer = dao.getAllBeer()
    val myResponse: MutableLiveData<List<Beer>> = MutableLiveData()

    suspend fun getRetrofit() = apiService.getAllBeer()

    suspend fun loadRetro() = dao.insertBeers(apiService.getAllBeer())


    suspend fun insert(beer: Beer): Long {
        return dao.insertBeer(beer)
    }

    suspend fun update(beer: Beer) {
        return dao.updateBeer(beer)
    }

    suspend fun delete(beer: Beer) {
        return dao.deleteBeer(beer)
    }

    fun deleteAllBeer() = dao.deleteAllBeer()

}