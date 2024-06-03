package com.jchunga.salesapp.presentation.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jchunga.salesapp.core.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(){

    private val _drawerState = MutableLiveData<DrawerState>(DrawerState(initialValue = DrawerValue.Closed))
    val drawerState : LiveData<DrawerState> = _drawerState

    private val _currentScreen = MutableLiveData<Screen>(Screen.PointSale)
    val currentScreen: LiveData<Screen> = _currentScreen

    fun openDrawer() {
        _drawerState.value = DrawerState(initialValue = DrawerValue.Open)
    }

    fun closeDrawer() {
        _drawerState.value = DrawerState(initialValue = DrawerValue.Open)
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
        closeDrawer()
    }

}