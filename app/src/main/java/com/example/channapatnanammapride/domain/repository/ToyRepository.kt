package com.example.channapatnanammapride.domain.repository

import com.example.channapatnanammapride.domain.model.Artisan
import com.example.channapatnanammapride.domain.model.Toy
import com.example.channapatnanammapride.domain.model.Workshop
import kotlinx.coroutines.flow.Flow

interface ToyRepository {
    fun verifyToy(toyId: String): Flow<Result<Pair<Toy, Artisan>>>
    fun getToyCatalog(): Flow<List<Toy>>
    fun getWorkshops(): Flow<List<Workshop>>
    fun getArtisan(artisanId: String): Flow<Artisan?>
}
