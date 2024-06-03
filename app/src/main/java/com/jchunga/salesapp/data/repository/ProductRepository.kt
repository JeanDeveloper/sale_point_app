package com.jchunga.salesapp.data.repository

import com.jchunga.salesapp.data.dao.ProductDao
import com.jchunga.salesapp.data.entity.Product
import com.jchunga.salesapp.domain.repository.IProductRepository
import javax.inject.Inject

class ProductRepository @Inject constructor( private val productDao: ProductDao) :  IProductRepository{

    override suspend fun createProduct(product: Product) = productDao.create( product )

    override suspend fun getAll(idPointSale: Int): List<Product> = productDao.getAll(idPointSale)

    override suspend fun getProduct(id: Int): Product  = productDao.getProduct(id)

}