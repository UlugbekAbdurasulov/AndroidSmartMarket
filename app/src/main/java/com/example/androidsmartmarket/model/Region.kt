package com.example.androidsmartmarket.model

data class WelcomeR (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: List<Datum>
)

data class Datum (
    val id: Long,
    val name: String,
    val districts: List<District>
)

data class District (
    val id: Long,
    val name: String,
    val region: Long
)
