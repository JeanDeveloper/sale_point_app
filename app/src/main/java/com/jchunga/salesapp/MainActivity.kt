package com.jchunga.salesapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.jchunga.salesapp.domain.usecase.CheckLoginStatusUseCase
import com.jchunga.salesapp.presentation.navigation.Navigation
import com.jchunga.salesapp.presentation.screen.SplashScreen
import com.jchunga.salesapp.presentation.viewmodel.MainViewModel
import com.jchunga.salesapp.ui.theme.SalesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var checkLoginStatusUseCase: CheckLoginStatusUseCase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.loadInitialData()

        setContent {
            val isDataLoaded by viewModel.isDataLoaded.collectAsState()
            SalesAppTheme {
                WindowCompat.setDecorFitsSystemWindows(window, false)
                if(isDataLoaded){
                    Navigation(checkLoginStatusUseCase)
                }else{
                    SplashScreen()
                }
            }
        }
    }
}