package com.example.weekseventask.adapter

data class ImageUploadResponse(
    val status : Int,
    val message :String,
    val payload :Payload
)

data class Payload(
    val fileId :String,
    val fileType :String,
    var fileName :String,
    val downloadUri :String,
    val uploadStatus :Boolean

)
