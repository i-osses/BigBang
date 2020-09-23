package com.prodev.bigbang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prodev.bigbang.model.repository.BeerRepository

class MainViewModelFactory(private val repository: BeerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }


}