package com.example.weekseventask.model

data class PokemonDataClass(
    val count: Int,
    val next: String,
    val previous: String,
    val results: ArrayList<Result>
)