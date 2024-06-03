package com.jchunga.salesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jchunga.salesapp.core.utils.Screen
import com.jchunga.salesapp.domain.usecase.CheckLoginStatusUseCase
import com.jchunga.salesapp.presentation.screen.HomeScreen
import com.jchunga.salesapp.presentation.screen.LoginScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jchunga.salesapp.presentation.screen.MapScreen
import com.jchunga.salesapp.presentation.screen.SplashScreen
import com.jchunga.salesapp.presentation.screen.VisitScreen

val localHomeNavController = compositionLocalOf<NavController> { error("HomeNavcontroller") }

@Composable
fun Navigation(
    checkLoginStatusUseCase: CheckLoginStatusUseCase? = null
) {
    val navController = rememberNavController()
    var isLoggedIn by remember {
        mutableStateOf(false) // Inicialmente, el usuario no está logeado
    }

    // Observar el estado de inicio de sesión cuando se inicializa o cambia
    LaunchedEffect(true) {
        isLoggedIn = checkLoginStatusUseCase?.invoke() ?: false
    }

    CompositionLocalProvider(value = localHomeNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) {
                Screen.Home.route
            } else {
                Screen.Login.route
            }
        ) {
            composable(Screen.Login.route){
                LoginScreen()
            }
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Splash.route){
                SplashScreen()
            }
            composable("${Screen.Visit.route}/{id}/{title}/{description}",
                arguments = listOf(
                    navArgument(
                        name = "id",
                    ){
                        type = NavType.IntType
                    },
                    navArgument(
                        name = "title",
                    ){
                        type = NavType.StringType
                    },
                    navArgument(
                        name = "description",
                    ){
                        type = NavType.StringType
                    },
                )
            ){
                val id: Int? = it.arguments?.getInt("id")
                val title: String? = it.arguments?.getString("title")
                val description: String? = it.arguments?.getString("description")
                VisitScreen(
                    id = id!!,
                    title = title!!,
                    description = description!!
                )
            }
            composable(
                "${Screen.Map.route}/{lat}/{lng}/{title}/{description}",
                arguments = listOf(
                    navArgument(
                        name = "lat",
                    ){
                        type = NavType.FloatType
                    },
                    navArgument(
                        name = "lng",
                    ){
                        type = NavType.FloatType
                    },
                    navArgument(
                        name = "title",
                    ){
                        type = NavType.StringType
                    },
                    navArgument(
                        name = "description",
                    ){
                        type = NavType.StringType
                    },
                )
            ){
                val lat = it.arguments?.getFloat("lat")
                val lng = it.arguments?.getFloat("lng")
                val title = it.arguments?.getString("title")
                val description = it.arguments?.getString("description")
                MapScreen(lat = lat!!, lng = lng!!, title!!, description!!)
            }
//            composable(
//                "${Screen.Product.route}/{id}/{title}",
//                arguments = listOf(
//                    navArgument(
//                        name = "id",
//                    ){
//                        type = NavType.IntType
//                    },
//                    navArgument(
//                        name = "title",
//                    ){
//                        type = NavType.StringType
//                    }
//                )
//            ){
//                val id = it.arguments?.getInt("id")
//                val title = it.arguments?.getString("title")
//                ProductScreen( id = id!!, title = title!!)
//            }
        }
    }

}