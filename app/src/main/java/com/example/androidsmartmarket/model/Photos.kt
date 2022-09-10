package com.example.androidsmartmarket.model

import java.io.Serializable
data class Welcome (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: Data
)

data class Data (
    val technicals: List<Family>,
    val family: List<Family>,
    val computers: List<Any?>
): Serializable

data class Family (
    val seller: Seller,
    val delivery_types_brings: List<DeliveryTypesBring>,
    val minAmount: Long,
    val oldPrice: Long? = null,
    val photos: List<Photos>,
    val category_id: Long,
    val price: Long,
    val orgID: Long,
    val name: String,
    val hasDiscount: Boolean? = null,
    val delivery_types_delivery: DeliveryTypesDelivery,
    val id: Long,
    val unitID: Long
): Serializable

data class DeliveryTypesBring (
    val id: Long,
    val address: String
): Serializable

data class DeliveryTypesDelivery (
    val available: Boolean
): Serializable

data class Photos (
    val id: String,
    val is_main: Boolean,
    val product_id: Long
): Serializable

data class Seller (
    val id: Long,
    val region_id: Long,
    val district_id: Long,
    val name: String,
    val address: String,
    val header_name: String,
    val mobile_phone: String? = null,
    val email: String? = null
): Serializable
