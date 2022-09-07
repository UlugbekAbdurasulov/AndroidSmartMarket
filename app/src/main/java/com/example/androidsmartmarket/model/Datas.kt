package com.example.androidsmartmarket.model


data class Welcomes (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: Datas
)

data class Datas (
    val id: Long,
    val name: String,
    val categoryID: Long,
    val unitID: Long,
    val unit: String,
    val makeName: String,
    val countryName: String,
    val price: Long,
    val amount: Long,
    val minAmount: Long,
    val expirationLife: String,
    val freeServiceLife: String,
    val ayear: Long,
    val technicalParameters: String,
    val photos: List<Photo>,
    val orgID: Long,
    val seller: Sellers,
    val delivery_types: DeliveryTypes
)

data class DeliveryTypes (
    val delivery: Delivery,
    val brings: List<Bring>
)

data class Bring (
    val id: Long,
    val address: String
)

data class Delivery (
    val available: Boolean
)

data class Photo (
    val id: String,
    val url: String,
    val isMain: Boolean,
    val productID: Long
)

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
)
