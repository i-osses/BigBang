package com.prodev.bigbang

import android.app.Application
import androidx.lifecycle.*
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.db.BeerDatabase
import com.prodev.bigbang.model.repository.BeerRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : BeerRepository

    val beer: LiveData<List<Beer>>

    init{
        repository = BeerRepository(BeerDatabase.getInstance(application).getDao(), application)
        beer = repository.getBeersFromDB
    }

    fun cacheData(){
        repository.insertNetworkData()
    }


}