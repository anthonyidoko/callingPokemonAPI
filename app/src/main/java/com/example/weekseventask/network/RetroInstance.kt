package com.example.networkcall.Network

import com.example.weekseventask.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroInstance {
//Create a retrofit object
    companion object {
//        private val Base_Url = "https://pokeapi.co/api/v2/"

        fun getRetroInstance() : RetroService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetroService::class.java)

        }

    }


}