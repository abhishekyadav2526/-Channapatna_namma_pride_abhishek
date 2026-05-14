package com.example.channapatnanammapride.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.channapatnanammapride.utils.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToVerify: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToHowItsMade: () -> Unit,
    onNavigateToMeetMaker: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val language = LanguageManager.currentLanguage.value
    val isEnglish = language == LanguageManager.Language.ENGLISH

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (isEnglish) "Channapatna Namma Pride" else "ಚನ್ನಪಟ್ಟಣ ನಮ್ಮ ಹೆಮ್ಮೆ",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    TextButton(onClick = { LanguageManager.toggleLanguage() }) {
                        Text(if (isEnglish) "ಕನ್ನಡ" else "English", color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            FeaturedBanner(isEnglish)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HomeCard(
                        title = if (isEnglish) "Verify My Toy" else "ನನ್ನ ಆಟಿಕೆಯನ್ನು ಪರಿಶೀಲಿಸಿ",
                        icon = Icons.Default.Verified,
                        color = Color(0xFFE57373),
                        onClick = onNavigateToVerify
                    )
                }
                item {
                    HomeCard(
                        title = if (isEnglish) "Toy Catalog" else "ಆಟಿಕೆ ಕ್ಯಾಟಲಾಗ್",
                        icon = Icons.Default.Category,
                        color = Color(0xFF81C784),
                        onClick = onNavigateToCatalog
                    )
                }
                item {
                    HomeCard(
                        title = if (isEnglish) "How It's Made" else "ಇದು ಹೇಗೆ ತಯಾರಾಗುತ್ತದೆ",
                        icon = Icons.Default.HistoryEdu,
                        color = Color(0xFF64B5F6),
                        onClick = onNavigateToHowItsMade
                    )
                }
                item {
                    HomeCard(
                        title = if (isEnglish) "Meet The Maker" else "ತಯಾರಕರನ್ನು ಭೇಟಿ ಮಾಡಿ",
                        icon = Icons.Default.Map,
                        color = Color(0xFFFFB74D),
                        onClick = onNavigateToMeetMaker
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedBanner(isEnglish: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (isEnglish) "Authentic Channapatna" else "ಅಪ್ಪಟ ಚನ್ನಪಟ್ಟಣ",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isEnglish) "Explore the Heritage" else "ಪರಂಪರೆಯನ್ನು ಅನ್ವೇಷಿಸಿ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }
    }
}

@Composable
fun HomeCard(title: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
