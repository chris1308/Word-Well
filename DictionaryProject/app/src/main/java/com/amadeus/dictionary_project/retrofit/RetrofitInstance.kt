package com.amadeus.dictionary_project.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/"

    //pembuatan object retrofit yang berfungsi untuk dapatkan instance dari DictApi
    //gsonconverterfactory bisa dipakai setelah add dependencies di gradle app
    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //dapatkan dictionary api dengan menggunakan getInstance
    val dictionaryApi : DictionaryApi = getInstance().create(DictionaryApi::class.java)


}