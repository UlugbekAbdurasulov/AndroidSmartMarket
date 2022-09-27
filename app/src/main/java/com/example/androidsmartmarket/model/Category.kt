package com.example.androidsmartmarket.model

import java.io.Serializable

data class Welcom (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: List<Datume>
)

data class Datume (
    val id: Long,
    val value: Map<String, DatumValue>,
    val title: String
):Serializable

data class DatumValue (
    val id: Long,
    val value: Map<String, ValueValueClass>,
    val title: String? = null
):Serializable

data class ValueValueClass (
    val id: Long,
    val name: String? = null
):Serializable
