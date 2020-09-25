package com.prodev.bigbang

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.db.BeerDatabase
import com.prodev.bigbang.model.repository.BeerRepository
import com.prodev.bigbang.model.util.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : BeerRepository

    val beer: LiveData<List<Beer>>

    init{
        repository = BeerRepository(BeerDatabase.getInstance(application).getDao())
        beer = repository.beer
    }
    val myResponse: MutableLiveData<List<Beer>> = MutableLiveData()

    val inputId = MutableLiveData<Int>().toString()

    val inputName = MutableLiveData<String>()

    val inputTip = MutableLiveData<String>()

    val inputDescription = MutableLiveData<String>()

    val inputImage = MutableLiveData<String>()

    fun getBeerRF() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getRetrofit()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }
}