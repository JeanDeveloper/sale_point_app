package com.jchunga.salesapp.domain.repository

import com.jchunga.salesapp.data.entity.Product

interface IProductRepository {

    suspend fun createProduct(product: Product)

    suspend fun getAll(idPointSale:Int): List<Product>

    suspend fun getProduct( id:Int ): Product

}