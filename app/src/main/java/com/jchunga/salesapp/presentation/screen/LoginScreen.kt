package com.jchunga.salesapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jchunga.salesapp.R
import com.jchunga.salesapp.core.utils.Screen
import com.jchunga.salesapp.presentation.common.CustomTextField
import com.jchunga.salesapp.presentation.navigation.localHomeNavController
import com.jchunga.salesapp.presentation.viewmodel.LoginViewModel
import com.jchunga.salesapp.ui.theme.Blue
import com.jchunga.salesapp.ui.theme.PoppinsFontFamily
import com.jchunga.salesapp.ui.theme.PoppinsSansFontFamily
import com.jchunga.salesapp.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {
    val navController = localHomeNavController.current
    LoginScreenContent(
        navigatorTo = {
            navController.navigate(it.route)
        },
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginScreenContent(
    navigatorTo:(Screen.Home) -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val username:String by loginViewModel.username.observeAsState(initial = "")
    val password:String by loginViewModel.password.observeAsState(initial = "");
    val isLoginEnable:Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    val isLoading:Boolean by loginViewModel.isLoading.observeAsState(initial = false)
    val passVisibility:Boolean  by loginViewModel.passVisibility.observeAsState( initial =false)
    val showSnackBar:Boolean  by loginViewModel.showSnackBar.observeAsState( false )

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {

        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .verticalScroll(rememberScrollState())
            ){
                TopSection()

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    label = "Usuario",
                    textState = username,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        loginViewModel.onLoginChanged(username = it, password = password)
                    }
                )

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    label = "Contraseña",
                    textState = password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = if(passVisibility)  VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if(passVisibility){
                            Icons.Filled.Visibility
                        }else{
                            Icons.Filled.VisibilityOff
                        }
                        IconButton(
                            onClick = { loginViewModel.changePassVisibility() }

                        ) {
                            Icon(
                                image,
                                contentDescription = null
                            )
                        }
                    },
                    onValueChange = {
                        loginViewModel.onLoginChanged(username= username, password = it)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = true,
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    ),
                    onClick = {
                        loginViewModel.onLoginSelected(){navigatorTo(Screen.Home) }
                    },
                ) {

                    if(isLoading){
                        CircularProgressIndicator()
                    }else{
                        Text(
                            text = "Iniciar Sesion",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = White
                            )
                        )
                    }
                }

                if(showSnackBar){
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Credenciales Invalidas")
                    }
                }

            }
        }
    }


}


@Composable
fun TopSection(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
        )
        Text(
            text = "APP GESTOR",
            fontSize = 40.sp,
            fontFamily = PoppinsFontFamily,
            color = Color.Black
        )
        Text(
            text = "Xplora",
            fontSize = 20.sp,
            fontFamily = PoppinsSansFontFamily,
            color = Color.Black
        )
        Text(
            text = "v.2.0.0",
            fontSize = 15.sp,
            fontFamily = PoppinsSansFontFamily,
            color = Color.Black
        )

    }

}


