package com.prodev.bigbang.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prodev.bigbang.model.Beer

@Database(entities = [Beer::class], version = 1)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun getDao() : BeerDAO

    companion object {

        @Volatile
        private var INSTANCE: BeerDatabase? = null
        fun getInstance(context: Context): BeerDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BeerDatabase::class.java,
                        "beer_database"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}