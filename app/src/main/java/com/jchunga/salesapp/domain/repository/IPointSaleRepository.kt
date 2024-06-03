package com.jchunga.salesapp.domain.repository

import com.jchunga.salesapp.data.entity.PointSale


interface IPointSaleRepository {

    suspend fun createPointSale(pointSale: PointSale)

    suspend fun getAll(): List<PointSale>

    suspend fun getPointSale( id:Int ): PointSale?
}