package com.example.channapatnanammapride.presentation.meetthemaker

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.channapatnanammapride.utils.LanguageManager
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetTheMakerScreen(
    onNavigateBack: () -> Unit,
    onNavigateToArtisan: (String) -> Unit,
    viewModel: MeetTheMakerViewModel = hiltViewModel()
) {
    val workshops by viewModel.workshops.collectAsState()
    val isEnglish = LanguageManager.currentLanguage.value == LanguageManager.Language.ENGLISH

    val channapatna = LatLng(12.6514, 77.2155)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(channapatna, 14f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEnglish) "Meet The Maker" else "ತಯಾರಕರನ್ನು ಭೇಟಿ ಮಾಡಿ") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            cameraPositionState = cameraPositionState
        ) {
            workshops.forEach { workshop ->
                Marker(
                    state = MarkerState(position = LatLng(workshop.latitude, workshop.longitude)),
                    title = if (isEnglish) workshop.name else workshop.nameKn,
                    snippet = if (isEnglish) "Tap to see artisans" else "ಕುಶಲಕರ್ಮಿಗಳನ್ನು ನೋಡಲು ಟ್ಯಾಪ್ ಮಾಡಿ",
                    onInfoWindowClick = {
                        if (workshop.artisanIds.isNotEmpty()) {
                            onNavigateToArtisan(workshop.artisanIds.first())
                        }
                    }
                )
            }
        }
    }
}
