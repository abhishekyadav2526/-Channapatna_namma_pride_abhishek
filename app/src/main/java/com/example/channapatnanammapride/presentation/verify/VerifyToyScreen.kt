package com.example.channapatnanammapride.presentation.verify

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.channapatnanammapride.domain.model.Artisan
import com.example.channapatnanammapride.domain.model.Toy
import com.example.channapatnanammapride.utils.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyToyScreen(
    onNavigateBack: () -> Unit,
    viewModel: VerifyToyViewModel = hiltViewModel()
) {
    var toyId by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val isEnglish = LanguageManager.currentLanguage.value == LanguageManager.Language.ENGLISH

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEnglish) "Verify My Toy" else "ನನ್ನ ಆಟಿಕೆಯನ್ನು ಪರಿಶೀಲಿಸಿ") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isEnglish) "Enter the 6-digit unique Toy ID" else "6-ಅಂಕಿಯ ವಿಶಿಷ್ಟ ಆಟಿಕೆ ಐಡಿಯನ್ನು ನಮೂದಿಸಿ",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = toyId,
                onValueChange = { if (it.length <= 6) toyId = it },
                label = { Text("Toy ID") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { viewModel.verifyToy(toyId) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(if (isEnglish) "Verify Authenticity" else "ಅಧಿಕೃತತೆಯನ್ನು ಪರಿಶೀಲಿಸಿ")
            }

            Spacer(modifier = Modifier.height(32.dp))

            when (val state = uiState) {
                is VerifyUiState.Loading -> CircularProgressIndicator()
                is VerifyUiState.Success -> VerificationResult(state.toy, state.artisan, isEnglish)
                is VerifyUiState.Error -> Text(state.message, color = MaterialTheme.colorScheme.error)
                VerifyUiState.Idle -> {}
            }
        }
    }
}

@Composable
fun VerificationResult(toy: Toy, artisan: Artisan, isEnglish: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Verified,
                contentDescription = null,
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = if (isEnglish) "Verified Authentic!" else "ಅಧಿಕೃತವೆಂದು ಪರಿಶೀಲಿಸಲಾಗಿದೆ!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (isEnglish) "Toy: ${toy.name}" else "ಆಟಿಕೆ: ${toy.nameKn}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = if (isEnglish) "Maker: ${artisan.name}" else "ತಯಾರಕರು: ${artisan.nameKn}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isEnglish) toy.story else toy.storyKn,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
