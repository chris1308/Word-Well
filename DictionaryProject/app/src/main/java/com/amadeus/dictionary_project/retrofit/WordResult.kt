package com.amadeus.dictionary_project.retrofit

//WordResult sebagai duplikat dari respons API yang diperoleh (JSON)
data class WordResult (
    val word:String,
    val phonetic : String?,
    val meanings: List<Meaning>
)

data class Meaning (
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class Definition (
    val definition: String,
)