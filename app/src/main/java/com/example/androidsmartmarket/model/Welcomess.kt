package com.example.androidsmartmarket.model

import java.io.Serializable

data class Welcomess (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: Datae
): Serializable

data class Datae (
    val categories: List<Category>
):Serializable

data class Category (
    val id: Long,
    val name: String? = null,
    val parent_id: Long,
    val icon: String? = null,
    val type: Type? = null,
    val amount: Long
):Serializable

enum class Type {
    Credit,
    Default
}

