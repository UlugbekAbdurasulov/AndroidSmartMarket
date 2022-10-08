package com.example.androidsmartmarket.model

import java.io.Serializable

data class Welcomee (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: Dataa
): Serializable

data class Dataa (
    val count: Long,
    val products: List<Producta>
):Serializable

data class Producta (
    val id: Long,
    val name: String,
    val regionID: Long,
    val districtID: Long,
    val categoryID: Long,
    val unitID: Long,
    val price: Long,
    val minAmount: Long,
    val orgID: Long,
    val hasDiscount: Boolean,
    val oldPrice: Any? = null,
    val column: Column,
    val seller: SellerCat,
    val rating: Long,
    val commentNumber: Long,
    val photos: List<PhotoCat>,
    val deliveryTypess: List<DeliveryTypess>,
    val deliveryTypes: DeliveryTypess
):Serializable

enum class Column {
    The12
}

data class DeliveryTypess (
    val delivery: Deliverye,
    val brings: List<Brin>
):Serializable

data class Brin (
    val id: Long,
    val address: String
):Serializable

data class Deliverye (
    val available: Boolean,
    val price: Long,
    val time: Long
):Serializable

data class PhotoCat (
    val id: String,
    val is_main: Boolean,
    val product_ID: Long
):Serializable

data class SellerCat (
    val id: Long,
    val regionID: Long,
    val districtID: Long,
    val name: Name,
    val address: Address,
    val headerName: HeaderName,
    val mobilePhone: String,
    val email: Email
):Serializable

enum class Address {
    ВатанQiyatSartolMfySartolQishlogI
}

enum class Email {
    Murodjonoblaqlov56552GmailCOM
}

enum class HeaderName {
    OblaqulovMurodjonRasuljonOGLi
}

enum class Name {
    ОооSartolSavdoServisMchj
}