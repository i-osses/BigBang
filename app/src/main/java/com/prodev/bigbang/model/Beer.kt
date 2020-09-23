package com.prodev.bigbang.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer_data_table")
data class Beer(
    @PrimaryKey
    @ColumnInfo(name = "beer_id")
    val id: Int,
    @ColumnInfo(name = "beer_name")
    val name: String,
    @ColumnInfo(name = "beer_description")
    val description: String,
    @ColumnInfo(name = "beer_image_url")
    val image_url: String,
    @ColumnInfo(name = "beer_brewers_tips")
    val brewers_tips: String


)