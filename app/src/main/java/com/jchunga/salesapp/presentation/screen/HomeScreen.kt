package com.jchunga.salesapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jchunga.salesapp.R
import com.jchunga.salesapp.core.utils.Screen
import com.jchunga.salesapp.data.entity.User
import com.jchunga.salesapp.presentation.viewmodel.HomeViewModel
import com.jchunga.salesapp.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen( homeViewModel: HomeViewModel = hiltViewModel() ) {
    val scope = rememberCoroutineScope()
    val currentScreen by homeViewModel.currentScreen.observeAsState()
    val drawerState by homeViewModel.drawerState.observeAsState(initial = DrawerState(initialValue = DrawerValue.Closed))

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {  },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Punto de Venta") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Abrir menú"
                                )
                            }
                        }
                    )
                },

                content = { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        when (currentScreen) {
                            Screen.Home -> PointSaleScreen()
                            Screen.PointSale -> PointSaleScreen()
                            Screen.Support -> SupportScreen()
                            Screen.Login -> Unit
                            Screen.PointSale -> Unit
                            Screen.Splash -> Unit
                            Screen.Support -> Unit
                            Screen.Map -> Unit
                            Screen.Visit -> Unit
                            Screen.Product -> Unit
                            null -> Unit
                        }
                    }
                }
            )
        }
    )

}

@Composable
fun CustomModalDrawerSheet(){
    ModalDrawerSheet {
        UserSection()
        Divider()
        CustomNavigationDrawerItem(
            label = "Punto de Venta",
            screen = Screen.PointSale
        )
        CustomNavigationDrawerItem(
            label = "Soporte",
            screen = Screen.Support
        )
        CustomNavigationDrawerItem(
            label = "Cerrar Sesion",
            screen = Screen.Login
        )
    }
}

@Composable
fun UserSection(
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val userAuth: User? by loginViewModel.userAuthenticated.observeAsState(null);

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircleAvatar(imageResource = R.drawable.circle_avatar_empty)
        Text(userAuth?.name.toString())
        Text(userAuth?.email.toString())
    }
}

@Composable
fun CircleAvatar(
    imageResource: Int,
    size: Int = 100,
    borderWidth: Int = 2
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .padding(borderWidth.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size((size - borderWidth * 2).dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun CustomNavigationDrawerItem (
    label: String,
    screen: Screen,
    homeViewModel: HomeViewModel = hiltViewModel(),
    ){
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            homeViewModel.navigateTo(screen)
        }
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,

        )
    }
}
