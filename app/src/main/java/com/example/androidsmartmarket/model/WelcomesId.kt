package com.example.androidsmartmarket.model

data class Welcomev (
    val error: Any? = null,
    val message: Any? = null,
    val timestamp: String,
    val status: Long,
    val path: Any? = null,
    val data: List<Datums>
)

data class Datums (
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val regionID: Long,
    val districtID: Long,
    val orgID: Long,
    val name: String,
    val identificationCode: String,
    val unitID: Long,
    val unitName: String,
    val makeName: String,
    val countryName: CountryName,
    val price: Long,
    val amount: Long,
    val minAmount: Long,
    val expirationLife: String? = null,
    val freeServiceLife: String? = null,
    val ayear: Long,
    val technicalParameters: String,
    val creditSellerID: Long,
    val file: String? = null,
    val mainPhoto: String,
    val hasDiscount: Boolean? = null,
    val oldPrice: Any? = null,
    val photos: String,
    val packageCode: Long,
    val packageName: PackageName,
    val privilegeID: Long? = null,
    val privilegeName: String
)

enum class CountryName {
    Ўзбекистон
}

enum class PackageName {
    Дона,
    Килограмм,
    Сўм
}
