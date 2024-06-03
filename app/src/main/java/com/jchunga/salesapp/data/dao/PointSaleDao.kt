package com.jchunga.salesapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.jchunga.salesapp.data.entity.PointSale;

@Dao
interface PointSaleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(pointSale: PointSale)

    @Query("SELECT * FROM sales")
    suspend fun getAll(): List<PointSale>

    @Query("SELECT * FROM sales WHERE id = :id")
    suspend fun getPointSale(id: Int): PointSale?

}
