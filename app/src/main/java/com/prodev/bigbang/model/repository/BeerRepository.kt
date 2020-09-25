package com.prodev.bigbang.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.db.BeerDAO
import com.prodev.bigbang.model.network.BeerService
import com.prodev.bigbang.model.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class BeerRepository(private val dao: BeerDAO, var context: Context) {

    val getBeersFromDB = dao.getAllBeer()

    private var retrofit: BeerService = RetrofitInstance.getInstance().create(BeerService::class.java)

    fun insertNetworkData(){
        retrofit.getAllBeer().enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {

                if(response.isSuccessful && response.body()!!.size>0)
                try {
                    CoroutineScope(IO).launch {
                        dao.insertBeers(response.body()!!)
                    }
                } catch (error1: IOException) {
                    Toast.makeText(context, "Error Retrofit", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {

                Log.i("TAG", "$t")
                Toast.makeText(context, "Network Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}