package com.jchunga.salesapp.presentation.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jchunga.salesapp.R
import com.jchunga.salesapp.core.utils.Screen
import com.jchunga.salesapp.data.entity.PointSale
import com.jchunga.salesapp.presentation.navigation.localHomeNavController
import com.jchunga.salesapp.presentation.viewmodel.PointSaleViewModel

@Composable
fun PointSaleCard(
    pointSale: PointSale,
    pointSaleViewModel: PointSaleViewModel = hiltViewModel()
){
    val navController = localHomeNavController.current
    val isDialogOpen by pointSaleViewModel.isDialogOpen.observeAsState(false)
    Card(
        Modifier
            .wrapContentSize()
            .padding(15.dp)
            .clickable {
                pointSaleViewModel.showDialog()
            },
        elevation = CardDefaults.cardElevation(8.dp),

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_map),
                    contentDescription = null,
                    modifier = Modifier
//                        .fillMaxHeight()
                        .clickable {
                            val lat: Float = pointSale.lat.toFloat()
                            val lng: Float = pointSale.longi.toFloat()
                            navController.navigate("${Screen.Map.route}/${lat}/${lng}/${pointSale.name}/${pointSale.address}")
                        }
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = pointSale.name,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    Text(
                        text = "Codigo: ${pointSale.code}",
                        style = TextStyle(fontWeight = FontWeight.W500, fontSize = 15.sp)
                    )
                    Text(
                        text = pointSale.address,
                        style = TextStyle(fontWeight = FontWeight.W500, fontSize = 15.sp)
                    )
                }

//                IconButton(
//                    modifier = Modifier
//                        .fillMaxHeight(),
//                    onClick = { /*TODO*/ },
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.right_arrow),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .fillMaxHeight()
//                    )
//                }
            }
        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { pointSaleViewModel.hideDialog() },
                title = { Text(pointSale.name) },
                text = { Text("¿Atenderá el PDV?") },
                confirmButton = {
                    Button(onClick = { pointSaleViewModel.hideDialog() }) {
                        Text("No")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            pointSaleViewModel.hideDialog()
                            navController.navigate("${Screen.Visit.route}/${pointSale.id}/${pointSale.name}/${pointSale.address}")
                        }
                    ) {
                        Text("Si")
                    }
                }
            )
        }
    }

}