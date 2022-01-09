package com.example.dormhelper.screens.network.dormsfromnetwork

import com.example.dormhelper.model.NetDorm
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.kortrijk.be/studentenvoorzieningen/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DormApiService {
    @GET("koten.json")
    suspend fun getProperties():
            List<NetDorm>
}

object DormApi {
    val retrofitService : DormApiService by lazy {
        retrofit.create(DormApiService::class.java) }
}