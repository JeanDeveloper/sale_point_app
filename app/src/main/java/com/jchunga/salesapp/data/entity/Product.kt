package com.jchunga.salesapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cost") val cost: Double,
    @ColumnInfo(name = "ruta_cost") val rutaCost: Double,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "id_point_sale") val idPointSale: Int
)