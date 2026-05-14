package com.example.channapatnanammapride.presentation.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.channapatnanammapride.utils.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToyDetailsScreen(
    toyId: String,
    onNavigateBack: () -> Unit,
    viewModel: ToyCatalogViewModel = hiltViewModel()
) {
    val toys by viewModel.toys.collectAsState()
    val toy = toys.find { it.id == toyId }
    val isEnglish = LanguageManager.currentLanguage.value == LanguageManager.Language.ENGLISH

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEnglish) "Toy Details" else "ಆಟಿಕೆ ವಿವರಗಳು") },
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
        toy?.let { t ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = t.imageUrl,
                    contentDescription = t.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isEnglish) t.name else t.nameKn,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Color(0xFF4CAF50))
                    }
                    Text(
                        text = if (isEnglish) t.category else "ಸಾಂಪ್ರದಾಯಿಕ",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isEnglish) "The Story" else "ಕಥೆ",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (isEnglish) t.story else t.storyKn,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (isEnglish) "Description" else "ವಿವರಣೆ",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (isEnglish) t.description else t.descriptionKn,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
