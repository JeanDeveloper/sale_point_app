package com.jchunga.salesapp.data.repository

import com.jchunga.salesapp.data.dao.PointSaleDao
import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.domain.repository.IPointSaleRepository
import javax.inject.Inject

class PointSaleRepository @Inject constructor( private val pointSaleDao: PointSaleDao) : IPointSaleRepository {
    override suspend fun createPointSale(pointSale: PointSale) = pointSaleDao.create(pointSale)

    override suspend fun getAll(): List<PointSale> = pointSaleDao.getAll()

    override suspend fun getPointSale(id: Int): PointSale? = pointSaleDao.getPointSale(id)

}