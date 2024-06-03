package com.jchunga.salesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class PointSale(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "code") val code: Long,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "latitud") val lat: String,
    @ColumnInfo(name = "longitud") val longi: String,
    @ColumnInfo(name = "photoPath") val photoPath : String = "",
)