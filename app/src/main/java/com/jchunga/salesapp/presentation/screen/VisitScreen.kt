package com.jchunga.salesapp.presentation.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jchunga.salesapp.presentation.navigation.localHomeNavController
import com.jchunga.salesapp.presentation.viewmodel.VisitViewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import java.io.File
import androidx.core.content.FileProvider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.jchunga.salesapp.core.utils.Screen


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun VisitScreen(
    id: Int ,
    title: String,
    description: String,
    visitViewModel: VisitViewModel = hiltViewModel()
){
    val navController = localHomeNavController.current
    val imageUri: Uri?  by visitViewModel.imageUri.observeAsState( )
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            photoUri?.let { visitViewModel.setImageUri(it) }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Visita") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        },

    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = title)
            Text(text = description)
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                if (visitViewModel.imageUri != null) {
                    Image(
                        painter = rememberImagePainter(visitViewModel.imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    IconButton(
                        onClick = {
                            val file = File(context.cacheDir, "temp_photo.jpg")
                            photoUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                            photoUri?.let {
                                launcher.launch(it)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = "Tomar foto",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
            OutlinedButton(
                onClick = {
//                    navController.navigate("${Screen.Product.route}/${id}/${title}")
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ){
                Text("Visitar")
            }
        }
    }
}