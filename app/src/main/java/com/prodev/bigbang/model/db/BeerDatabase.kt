package com.prodev.bigbang.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prodev.bigbang.model.Beer

@Database(entities = [Beer::class], version = 1)
abstract class BeerDatabase : RoomDatabase() {

    abstract val beerDAO: BeerDAO

    companion object {

        @Volatile
        private var INSTANCE: BeerDatabase? = null
        fun getInstance(context: Context): BeerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BeerDatabase::class.java,
                        "beer_database"
                    ).build()
                }
                return instance
            }
        }
    }
}