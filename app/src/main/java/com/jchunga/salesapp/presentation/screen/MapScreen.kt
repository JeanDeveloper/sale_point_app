package com.jchunga.salesapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jchunga.salesapp.presentation.navigation.localHomeNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    lat:Float,
    lng:Float,
    title: String,
    description: String,
) {
    val marker = LatLng( lat.toDouble(), lng.toDouble() )
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(marker, 15f)
    }
    val navController = localHomeNavController.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ){
        GoogleMap(
            modifier = Modifier,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
            ),
            properties = MapProperties(
                mapType = MapType.HYBRID
            ),
            cameraPositionState = cameraPositionState,
        ){
            Marker(
                state = MarkerState(position = marker),
                title = title,
                snippet = description
            )
        }
    }

    LaunchedEffect(Unit) {
        cameraPositionState.animate(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(marker, 15f)
        )
    }
}