package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.Product
import com.jchunga.salesapp.data.repository.ProductRepository
import com.jchunga.salesapp.domain.repository.IProductRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) = productRepository.createProduct(product)
}