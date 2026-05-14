package com.example.channapatnanammapride.data.repository

import com.example.channapatnanammapride.domain.model.Artisan
import com.example.channapatnanammapride.domain.model.Toy
import com.example.channapatnanammapride.domain.model.Workshop
import com.example.channapatnanammapride.domain.repository.ToyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToyRepositoryImpl @Inject constructor() : ToyRepository {

    private val mockArtisans = listOf(
        Artisan("a1", "Ramesh Babu", "ರಮೇಶ್ ಬಾಬು", "https://example.com/artisan1.jpg", "Expert in wooden horses.", "ಮರದ ಕುದುರೆಗಳಲ್ಲಿ ಪರಿಣಿತರು.", "w1", "9876543210"),
        Artisan("a2", "Shanthamma", "ಶಾಂತಮ್ಮ", "https://example.com/artisan2.jpg", "Specialist in dolls.", "ಬೊಂಬೆಗಳಲ್ಲಿ ಪರಿಣತರು.", "w2", "9876543211"),
        Artisan("a3", "Kumaraswamy", "ಕುಮಾರಸ್ವಾಮಿ", "https://example.com/artisan3.jpg", "Expert in spinning tops.", "ಬುಗುರಿಗಳನ್ನು ತಯಾರಿಸುವಲ್ಲಿ ಪರಿಣಿತರು.", "w3", "9876543212"),
        Artisan("a4", "Lakshmi", "ಲಕ್ಷ್ಮಿ", "https://example.com/artisan4.jpg", "Toy painter for 20 years.", "20 ವರ್ಷಗಳಿಂದ ಆಟಿಕೆಗಳಿಗೆ ಬಣ್ಣ ಹಚ್ಚುವವರು.", "w4", "9876543213"),
        Artisan("a5", "Mohan", "ಮೋಹನ್", "https://example.com/artisan5.jpg", "Master of lacquerware.", "ಲ್ಯಾಕ್ವೆರ್ವೇರ್ ಮಾಸ್ಟರ್.", "w5", "9876543214")
    )

    private val mockToys = listOf(
        Toy("123456", "Rocking Horse", "ತೂಗಾಡುವ ಕುದುರೆ", "Traditional", "Handcrafted rocking horse.", "ಕೈಯಿಂದ ಮಾಡಿದ ತೂಗಾಡುವ ಕುದುರೆ.", "https://example.com/toy1.jpg", "a1", true, "This horse represents the spirit of Channapatna.", "ಈ ಕುದುರೆಯು ಚನ್ನಪಟ್ಟಣದ ಉತ್ಸಾಹವನ್ನು ಪ್ರತಿನಿಧಿಸುತ್ತದೆ."),
        Toy("654321", "Wooden Dolls", "ಮರದ ಬೊಂಬೆಗಳು", "Dolls", "Traditional Channapatna dolls.", "ಸಾಂಪ್ರದಾಯಿಕ ಚನ್ನಪಟ್ಟಣದ ಬೊಂಬೆಗಳು.", "https://example.com/toy2.jpg", "a2", true, "Classic lacquerware dolls.", "ಕ್ಲಾಸಿಕ್ ಲ್ಯಾಕ್ವೆರ್ವೇರ್ ಬೊಂಬೆಗಳು."),
        Toy("111111", "Spinning Top", "ಬುಗುರಿ", "Educational", "Colorful spinning tops.", "ಬಣ್ಣಬಣ್ಣದ ಬುಗುರಿಗಳು.", "https://example.com/toy3.jpg", "a3", true, "Centuries old design.", "ಶತಮಾನಗಳಷ್ಟು ಹಳೆಯ ವಿನ್ಯಾಸ."),
        Toy("222222", "Wooden Train", "ಮರದ ರೈಲು", "Cars", "Smooth wooden train set.", "ನಯವಾದ ಮರದ ರೈಲು ಸೆಟ್.", "https://example.com/toy4.jpg", "a5", true, "Safe for kids to play.", "ಮಕ್ಕಳು ಆಟವಾಡಲು ಸುರಕ್ಷಿತ.")
    )

    private val mockWorkshops = listOf(
        Workshop("w1", "Sri Gombe Art", "ಶ್ರೀ ಗೊಂಬೆ ಆರ್ಟ್", "Channapatna Main Road", "ಚನ್ನಪಟ್ಟಣ ಮುಖ್ಯ ರಸ್ತೆ", 12.6514, 77.2155, listOf("a1")),
        Workshop("w2", "Namma Heritage", "ನಮ್ಮ ಹೆರಿಟೇಜ್", "Artisan Lane", "ಕುಶಲಕರ್ಮಿ ಗಲ್ಲಿ", 12.6530, 77.2180, listOf("a2")),
        Workshop("w3", "Channapatna Crafts", "ಚನ್ನಪಟ್ಟಣ ಕ್ರಾಫ್ಟ್ಸ್", "Old Town", "ಹಳೆಯ ಪೇಟೆ", 12.6550, 77.2120, listOf("a3")),
        Workshop("w4", "Vibrant Wood", "ವೈಬ್ರಂಟ್ ವುಡ್", "Market Road", "ಮಾರುಕಟ್ಟೆ ರಸ್ತೆ", 12.6490, 77.2190, listOf("a4")),
        Workshop("w5", "Lacquer World", "ಲ್ಯಾಕ್ಕರ್ ವರ್ಲ್ಡ್", "NH 275", "ಎನ್ಎಚ್ 275", 12.6600, 77.2100, listOf("a5"))
    )


    override fun verifyToy(toyId: String): Flow<Result<Pair<Toy, Artisan>>> = flow {
        delay(1000) // Simulate network delay
        val toy = mockToys.find { it.id == toyId }
        if (toy != null) {
            val artisan = mockArtisans.find { it.id == toy.artisanId }
            if (artisan != null) {
                emit(Result.success(toy to artisan))
            } else {
                emit(Result.failure(Exception("Artisan not found")))
            }
        } else {
            emit(Result.failure(Exception("Invalid Toy ID")))
        }
    }

    override fun getToyCatalog(): Flow<List<Toy>> = flow {
        emit(mockToys)
    }

    override fun getWorkshops(): Flow<List<Workshop>> = flow {
        emit(mockWorkshops)
    }

    override fun getArtisan(artisanId: String): Flow<Artisan?> = flow {
        emit(mockArtisans.find { it.id == artisanId })
    }
}
