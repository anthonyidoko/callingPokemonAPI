package com.example.fetchingapidata.network

import com.example.fetchingapidata.data.PokemonData
import com.example.fetchingapidata.data.PokemonDetail
import com.example.fetchingapidata.data.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    //Define api end point
    @GET("pokemon")
    suspend fun getPokemonFromRange(
        @Query("limit") limit :Int,
        @Query("offset") offset :Int
    ) : PokemonData

    @GET("pokemon/{id}/")
    suspend fun getSpecificPokemon(@Path("id") id :String): PokemonDetail

}