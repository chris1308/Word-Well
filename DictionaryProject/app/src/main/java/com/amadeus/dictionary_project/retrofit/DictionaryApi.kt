package com.amadeus.dictionary_project.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//semacam kumpulan fungsi untuk ambil data dari API
interface DictionaryApi {
    @GET("en/{word}")
    suspend fun getMeaning(@Path("word") word:String) : Response<List<WordResult>>
}