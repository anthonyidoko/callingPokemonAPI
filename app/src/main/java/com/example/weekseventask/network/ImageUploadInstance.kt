package com.example.weekseventask.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ImageUploadInstance {


    companion object {
        //Create a retrofit object
        fun getRetroInstance() : ImageUploadInstance {

            val baseUrl = "http://localhost:8080/api/v1/"
            val okHttpClient  = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build()
                .create(ImageUploadInstance::class.java)

        }

    }
}