package com.example.blogexplorer.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .build()

   private val retrofit by lazy {
       Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(client)
           .addConverterFactory(MoshiConverterFactory.create())
           .build()
   }

    val  api: BlogApi by lazy {
        retrofit.create(BlogApi::class.java)
    }
}