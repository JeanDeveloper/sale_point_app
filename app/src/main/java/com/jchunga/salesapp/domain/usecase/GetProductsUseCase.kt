package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.Product
import com.jchunga.salesapp.data.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int) = productRepository.getAll(id)
}