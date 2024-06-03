package com.jchunga.salesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.jchunga.salesapp.core.utils.Screen
import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _showSnackBar = MutableLiveData<Boolean>(false)
    val showSnackBar: LiveData<Boolean> = _showSnackBar

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _passVisibility = MutableLiveData<Boolean>()
    val passVisibility: LiveData<Boolean> = _passVisibility

    private var _userAuthenticated = MutableLiveData<User?>(null)
    var userAuthenticated: LiveData<User?> = _userAuthenticated

    fun changePassVisibility(){
        _passVisibility.value = !(_passVisibility.value ?: false)
    }

    fun onLoginChanged(username:String, password: String){
        _username.value = username
        _password.value = password
        _isLoginEnable.value = enableLogin(username, password)
    }

    private fun enableLogin(username:String, password:String) = username.isNotBlank() && password.length > 4

    fun onLoginSelected(navigatorTo:(Screen.Home) -> Unit){
        viewModelScope.launch {
            _isLoading.value = true
            val userResult = loginUseCase(_username.value.toString(), _password.value.toString())
            if( userResult != null ) {
                _userAuthenticated.value = userResult
                navigatorTo(Screen.Home)
                _showSnackBar.value = false
            }else{
                _userAuthenticated.value = null
                _showSnackBar.value = true
            }
            _isLoading.value = false
        }
    }

}
