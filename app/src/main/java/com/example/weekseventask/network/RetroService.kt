package com.example.networkcall.Network


import com.example.weekseventask.model.PokemonDataClass
import com.example.weekseventask.model.PokemonDetailClass
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {
//Fetch data from end point
    @GET("pokemon")
    suspend fun getAllDataFromApi(@Query("limit") limit :Int,
                                  @Query("offset") offset :Int
    ) :PokemonDataClass

// Get A specific pokemon
    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonDetailFromApi(@Path("pokemonId") limit :String
    ) :PokemonDetailClass


}