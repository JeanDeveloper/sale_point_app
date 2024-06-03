package com.jchunga.salesapp.models

import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.data.entity.Product

data class PointScreenState(
    val points: List<PointSale> = emptyList(),
    val productsPoint: List<Product> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val isLoading:Boolean = false
)
