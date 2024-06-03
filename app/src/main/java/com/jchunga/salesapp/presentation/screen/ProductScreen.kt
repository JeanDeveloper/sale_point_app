//package com.jchunga.salesapp.presentation.screen
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.jchunga.salesapp.models.PointScreenState
//import com.jchunga.salesapp.presentation.navigation.localHomeNavController
//import com.jchunga.salesapp.presentation.viewmodel.PointSaleViewModel
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProductScreen(
//    id: Int,
//    title: String,
//    pointSaleViewModel: PointSaleViewModel = hiltViewModel()
//){
//
//    val state by pointSaleViewModel.state.observeAsState(PointScreenState())
//
//    pointSaleViewModel.getProducts(id)
//
//    val navController = localHomeNavController.current
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = { Text( title ) },
//                navigationIcon = {
//                    IconButton(
//                        onClick = { navController.popBackStack() }
//                    ) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Atrás"
//                        )
//                    }
//                }
//            )
//        }
//    ){
//
//        LazyColumn(Modifier.padding(8.dp)) {
//            item {
//                Row(
//                    Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    TableCell(
//                        text = "Producto",
//                        alignment = TextAlign.Left,
//                        title = true
//                    )
//                    TableCell(text = "P. Costo", title = true)
//                    TableCell(text = "P. Mayor", title = true)
//                    TableCell(
//                        text = "Amount",
//                        alignment = TextAlign.Right,
//                        title = true
//                    )
//                }
//                HorizontalDivider(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .fillMaxWidth(),
//                    color = Color.LightGray
//                )
//            }
//
//            itemsIndexed(state.productsPoint.size) { _, invoice ->
//                Row(
//                    Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    TableCell(
//                        text = invoice.invoice,
//                        weight = column1Weight,
//                        alignment = TextAlign.Left
//                    )
//                    TableCell(text = invoice.date)
//                    StatusCell(text = invoice.status)
//                    TableCell(
//                        text = invoice.amount,
//                        alignment = TextAlign.Right
//                    )
//                }
//                HorizontalDivider(
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .fillMaxWidth(),
//                    color = Color.LightGray
//                )
//            }
//        }
//
//
//
//
//
//    }
//}