package com.example.weekseventask.network

import com.example.weekseventask.adapter.ImageUploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUploadService {

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image :MultipartBody.Part,
        @Part("description") description :RequestBody
    ) : Call<ImageUploadResponse>
    companion object{
        operator fun invoke() :ImageUploadService{
            return Retrofit.Builder()
                .baseUrl("https://darot-image-upload-service.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageUploadService::class.java)
        }
    }
}