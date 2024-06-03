package com.jchunga.salesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jchunga.salesapp.data.entity.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(product: Product)

    @Query("SELECT * FROM products WHERE id_point_sale = :idPointSale")
    suspend fun getAll(idPointSale:Int): List<Product>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): Product

}