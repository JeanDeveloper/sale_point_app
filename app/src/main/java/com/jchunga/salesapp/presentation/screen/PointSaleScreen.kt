package com.jchunga.salesapp.presentation.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jchunga.salesapp.models.PointScreenState
import com.jchunga.salesapp.presentation.common.PointSaleCard
import com.jchunga.salesapp.presentation.viewmodel.PointSaleViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PointSaleScreen(
    pointSaleViewModel: PointSaleViewModel = hiltViewModel()
){
    val state by pointSaleViewModel.state.observeAsState(PointScreenState())

    Scaffold (
        modifier = Modifier.background(Color.Transparent),
        content = {
            LazyColumn {
                items(state.points.size) {
//                    if (it >= state.points.size - 1 && !state.endReached && !state.isLoading) {
//                                  pointSaleViewModel.loadNextPoints()
//                    }
                    PointSaleCard(pointSale = state.points[it])
                }

            }
        },
        containerColor = Color.Transparent
    )
}
