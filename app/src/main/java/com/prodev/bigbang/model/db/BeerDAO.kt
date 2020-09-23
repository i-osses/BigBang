package com.prodev.bigbang.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prodev.bigbang.model.Beer

@Dao
interface BeerDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBeer(beer: Beer) : Long

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBeers(BeerList: List<Beer>)

    @Update
    suspend fun updateBeer(beer: Beer)

    @Delete
    suspend fun deleteBeer(beer: Beer)

    @Query("SELECT * FROM beer_data_table")
    fun getAllBeer():LiveData<List<Beer>>

    @Query("DELETE FROM beer_data_table")
    fun deleteAllBeer()

}