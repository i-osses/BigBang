package com.prodev.bigbang

import androidx.lifecycle.*
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.repository.BeerRepository
import com.prodev.bigbang.model.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: BeerRepository) : ViewModel() {

    val beer = repository.beer
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