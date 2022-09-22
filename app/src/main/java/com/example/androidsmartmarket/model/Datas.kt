package com.example.androidsmartmarket.model

import java.io.Serializable


data class Welcomes (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: Datas
):Serializable

data class Datas (
    val id: Long,
    val name: String,
    val category_id: Long,
    val unitID: Long,
    val unit: String,
    val makeName: String,
    val countryName: String,
    val price: Long,
    val amount: Long,
    val min_amount: Long,
    val expirationLife: String,
    val freeServiceLife: String,
    val ayear: Long,
    val technicalParameters: String,
    val photos: List<Photo>,
    val orgID: Long,
    val seller: Sellers,
    val delivery_types: DeliveryTypes
):Serializable

data class DeliveryTypes (
    val delivery: Delivery,
    val brings: List<Bring>
):Serializable

data class Bring (
    val id: Long,
    val address: String
):Serializable

data class Delivery (
    val available: Boolean
):Serializable

data class Photo (
    val id: String,
    val url: String,
    val is_main: Boolean,
    val product_id: Long
):Serializable

data class Sellers (
    val id: Long,
    val region_id: Long,
    val district_id: Long,
    val name: String,
    val address: String,
    val header_name: String,
    val mobile_phone: String,
    val email: Any? = null,
    val fullAddress: String
):Serializable
