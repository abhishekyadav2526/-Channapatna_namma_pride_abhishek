package com.example.channapatnanammapride.presentation.howitsmade

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.channapatnanammapride.utils.LanguageManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowItsMadeScreen(onNavigateBack: () -> Unit) {
    val isEnglish = LanguageManager.currentLanguage.value == LanguageManager.Language.ENGLISH
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEnglish) "How It's Made" else "ಇದು ಹೇಗೆ ತಯಾರಾಗುತ್ತದೆ") },
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
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            ProcessStep(
                title = if (isEnglish) "Step 1: Hale Wood Selection" else "ಹಂತ 1: ಹಾಲೆ ಮರದ ಆಯ್ಕೆ",
                description = if (isEnglish) "The process begins with selecting high-quality Hale Wood (Aivoia lucida), known for its soft texture and white color." 
                             else "ಹಾಲೆ ಮರವು ಮೆತ್ತಗಿರಲು ಮತ್ತು ಬಿಳಿ ಬಣ್ಣ ಹೊಂದಿರಲು ಹೆಸರುವಾಸಿಯಾಗಿದೆ.",
                color = Color(0xFF8D6E63)
            )
            ProcessStep(
                title = if (isEnglish) "Step 2: Turning on Lathe" else "ಹಂತ 2: ಲೇತ್‌ನಲ್ಲಿ ತಿರುಗಿಸುವುದು",
                description = if (isEnglish) "The wood is turned on a traditional lathe to achieve various shapes like spheres, cylinders, and more."
                             else "ಮರವನ್ನು ಸಾಂಪ್ರದಾಯಿಕ ಲೇತ್ ಮೇಲೆ ವಿವಿಧ ಆಕಾರಗಳಿಗೆ ತಿರುಗಿಸಲಾಗುತ್ತದೆ.",
                color = Color(0xFF64B5F6)
            )
            ProcessStep(
                title = if (isEnglish) "Step 3: Lac Dyeing" else "ಹಂತ 3: ಲ್ಯಾಕ್ ಡೈಯಿಂಗ್",
                description = if (isEnglish) "Natural lac dyes made from vegetable juices are applied while the wood is still turning, creating a glossy finish."
                             else "ಸಸ್ಯಗಳಿಂದ ತಯಾರಿಸಿದ ನೈಸರ್ಗಿಕ ಬಣ್ಣಗಳನ್ನು ಮರ ತಿರುಗುತ್ತಿರುವಾಗಲೇ ಹಚ್ಚಲಾಗುತ್ತದೆ.",
                color = Color(0xFFFFD54F)
            )
            ProcessStep(
                title = if (isEnglish) "Step 4: Polishing" else "ಹಂತ 4: ಹೊಳಪು ನೀಡುವುದು",
                description = if (isEnglish) "A screw-pine leaf is used to polish the toy to a high shine without any toxic chemicals."
                             else "ಯಾವುದೇ ವಿಷಕಾರಿ ರಾಸಾಯನಿಕಗಳಿಲ್ಲದೆ ಹೊಳಪು ನೀಡಲು ಸ್ಕ್ರೂ-ಪೈನ್ ಎಲೆಯನ್ನು ಬಳಸಲಾಗುತ್ತದೆ.",
                color = Color(0xFF81C784)
            )
        }
    }
}

@Composable
fun ProcessStep(title: String, description: String, color: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color, RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
