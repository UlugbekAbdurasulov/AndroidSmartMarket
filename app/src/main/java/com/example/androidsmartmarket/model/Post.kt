package com.example.androidsmartmarket.model

data class Post(var id: Int, var region_id: Int, var district_id: Int, var name: String, var address: String, var header_name : String, var mobile_phone : Int, var email: String,
                var delivery_types_brings : ArrayList<String>, var min_amount : Int, var old_price: Int, var price: Int
)
