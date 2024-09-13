package com.weatherapp.ui

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.weatherapp.MainViewModel

@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {}
    val camPosState = rememberCameraPositionState ()

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        onMapClick = { viewModel.add("Nova cidade", location = it)
        },
        cameraPositionState = camPosState
    ) {
        viewModel.cities.forEach {
            if (it.location != null) {
                Marker(
                    state = MarkerState(position = it.location!!),
                    title = it.name,
                    snippet = "${it.location}"
                )
            }
        }
        val recife = LatLng(-8.05, -34.9)
        val caruaru = LatLng(-8.27, -35.98)
        val joaopessoa = LatLng(-7.12, -34.84)
        viewModel.cities.forEach {
            if (it.location != null) {
                Marker(
                    state = MarkerState(position = it.location!!),
                    title = it.name,
                    snippet = "${it.location}"
                )
            }
        }
        Marker(
            state = MarkerState(position = recife),
            title = "Recife",
            snippet = "Marcador em Recife",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE) // Cor azul
        )

        Marker(
            state = MarkerState(position = caruaru),
            title = "Caruaru",
            snippet = "Marcador em Caruaru",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN) // Cor verde
        )

        Marker(
            state = MarkerState(position = joaopessoa),
            title = "João Pessoa",
            snippet = "Marcador em João Pessoa",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) // Cor vermelha
        )
    }
}