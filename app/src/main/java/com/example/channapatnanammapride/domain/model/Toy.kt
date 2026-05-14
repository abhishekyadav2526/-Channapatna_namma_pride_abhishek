package com.example.channapatnanammapride.domain.model

data class Toy(
    val id: String = "",
    val name: String = "",
    val nameKn: String = "",
    val category: String = "",
    val description: String = "",
    val descriptionKn: String = "",
    val imageUrl: String = "",
    val artisanId: String = "",
    val isAuthentic: Boolean = true,
    val story: String = "",
    val storyKn: String = ""
)
