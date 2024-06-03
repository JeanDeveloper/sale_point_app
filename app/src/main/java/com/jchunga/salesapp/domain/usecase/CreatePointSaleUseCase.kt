package com.jchunga.salesapp.domain.usecase

import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.data.repository.PointSaleRepository
import com.jchunga.salesapp.domain.repository.IPointSaleRepository
import javax.inject.Inject

class CreatePointSaleUseCase @Inject constructor(
    private val pointSalesRepository: PointSaleRepository
){
    suspend operator fun invoke(pointSale: PointSale) = pointSalesRepository.createPointSale(pointSale)
}