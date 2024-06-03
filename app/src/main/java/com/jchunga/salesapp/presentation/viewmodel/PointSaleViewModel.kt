package com.jchunga.salesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jchunga.salesapp.data.repository.PointSaleRepository
import com.jchunga.salesapp.models.PointScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.jchunga.salesapp.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class PointSaleViewModel @Inject constructor(
    private val pointSaleRepository: PointSaleRepository,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PointScreenState>(PointScreenState())
    val state : LiveData<PointScreenState> = _state

    private val _isDialogOpen = MutableLiveData<Boolean>(false)
    val isDialogOpen : LiveData<Boolean> = _isDialogOpen

    private val _id = MutableLiveData<Int>()
    var id : LiveData<Int> = _id

    init {
        viewModelScope.launch {
            val response = pointSaleRepository.getAll()
            _state.value = _state.value!!.copy(
                points = response
            )
        }
    }

    fun getProducts( id:Int ){
        _id.value = id
        viewModelScope.launch {
            try {
                val response = _id.value?.let { getProductsUseCase(it) }
                if(response.isNullOrEmpty()){
                    _state.value = response?.let {
                        _state.value!!.copy(
                            productsPoint  = it
                        )
                    }
                }
            }catch (e: Exception){
                _state.value = _state.value!!.copy(
                    error= e.message
                )
            }
        }
    }

    fun showDialog() {
        _isDialogOpen.value = true
    }

    fun hideDialog() {
        _isDialogOpen.value = false
    }

}