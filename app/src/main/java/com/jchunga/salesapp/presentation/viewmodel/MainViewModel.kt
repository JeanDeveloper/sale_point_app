package com.jchunga.salesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.salesapp.core.utils.pointSalesModel
//import com.jchunga.salesapp.core.utils.pointSalesModel
import com.jchunga.salesapp.core.utils.productsModel
import com.jchunga.salesapp.core.utils.usersModel
import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.domain.usecase.CreatePointSaleUseCase
import com.jchunga.salesapp.domain.usecase.CreateProductUseCase
import com.jchunga.salesapp.domain.usecase.CreateUserUseCase
import com.jchunga.salesapp.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val createPointSaleUseCase: CreatePointSaleUseCase,
    private val createProductUseCase: CreateProductUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _isDataLoaded = MutableStateFlow(false)
    val isDataLoaded: StateFlow<Boolean> = _isDataLoaded

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun loadInitialData() {
        viewModelScope.launch {
            val initialUsers = getUsersUseCase()
            if (initialUsers.isEmpty()) {

                usersModel.forEach(){ us ->
                    createUserUseCase(us)
                }

                pointSalesModel.forEach(){ point ->
                    createPointSaleUseCase(point)
                }

                productsModel.forEach(){prod ->
                    createProductUseCase(prod)
                }

            }
            _users.value = getUsersUseCase()
            _isDataLoaded.value = true
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _users.value = getUsersUseCase()
        }
    }


    fun insertUser(user: User) {
        viewModelScope.launch {
            createUserUseCase(user)
            loadUsers()
        }
    }



}