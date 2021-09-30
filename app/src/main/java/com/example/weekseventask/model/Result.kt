package com.example.weekseventask.model

class Result(
    val name: String,
    val url: String
){
    fun getImageUrlNumber() :String = url.substring(34,url.length-1)

    fun getBaseUrl() = url.substring(0,34)


}