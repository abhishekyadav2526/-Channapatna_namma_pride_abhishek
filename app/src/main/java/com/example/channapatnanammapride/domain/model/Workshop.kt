package com.example.channapatnanammapride.domain.model

data class Workshop(
    val id: String = "",
    val name: String = "",
    val nameKn: String = "",
    val address: String = "",
    val addressKn: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val artisanIds: List<String> = emptyList()
)
