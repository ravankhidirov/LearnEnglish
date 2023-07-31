package com.example.learnenglish.books.definition.Interface

import com.example.learnenglish.books.definition.data.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("entries/en/{word}")
    fun getDefinition(@Path("word") word: String): Call<List<Result>?>?
}