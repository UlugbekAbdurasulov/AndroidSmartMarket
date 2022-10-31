package com.example.androidsmartmarket.utils

import androidx.room.TypeConverter
import com.example.androidsmartmarket.model.Datas
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HobbiesTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun recipeToString(recipe: Datas): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToRecipe(recipeString: String): Datas {
        val objectType = object : TypeToken<Datas>() {}.type
        return gson.fromJson(recipeString, objectType)
    }
}