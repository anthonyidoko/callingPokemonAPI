package com.example.weekseventask.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.weekseventask.adapter.ImageUploadResponse
import com.example.weekseventask.network.ImageUploadService
import com.example.weekseventask.R
import com.example.weekseventask.getFileName
import com.example.weekseventask.snackbar
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class ImageUploadActivity : AppCompatActivity(),UploadImageRequqestBody.UploadCallback{

    companion object{
        private const val REQUEST_CODE_IMAGE_CHOOSER = 200
    }

    lateinit var imageView :ImageView
    lateinit var progressBar :ProgressBar
    var selectedImage :Uri? = null
    lateinit var rootLayout : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        val btnUpload = findViewById<Button>(R.id.btnUpload)
        rootLayout = findViewById(R.id.rootLayout)
        progressBar = findViewById(R.id.progressBar)

        btnUpload.setOnClickListener {
            uploadImage()
        }

        imageView = findViewById(R.id.imageView2)
        imageView.setOnClickListener {
            openImageChooser()
        }


    }

    private fun uploadImage() {
        if (selectedImage == null){
            rootLayout.snackbar("select an image first")

             return
        }
        val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImage!!,"r",null)?:return
        val file = File(cacheDir,contentResolver.getFileName(selectedImage!! ))
        val inputStraem = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStraem.copyTo(outputStream)

        progressBar.progress = 0
        val body = UploadImageRequqestBody(file,"image",this)
        ImageUploadService().uploadImage(
            MultipartBody.Part.createFormData("file",file.name,body),
            RequestBody.create(MediaType.parse("multipart/form-data"),"uploaded image")
        ).enqueue(object :Callback<ImageUploadResponse>{
            override fun onResponse(
                call: Call<ImageUploadResponse>,
                response: Response<ImageUploadResponse>
            ) {
                Log.d("ImageUpload", response.body().toString())
                progressBar.progress = 100
                rootLayout.snackbar(response.body()?.message.toString() )

            }

            override fun onFailure(call: Call<ImageUploadResponse>, t: Throwable) {
                rootLayout.snackbar(t.message!!)
            }

        })


    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpg","image/jpeg","image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
            startActivityForResult(it, REQUEST_CODE_IMAGE_CHOOSER)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode){
                 REQUEST_CODE_IMAGE_CHOOSER ->{
                     selectedImage = data?.data
                     imageView.setImageURI(selectedImage)
                 }
            }

        }
    }

    override fun onProgreeUpdate(percentage: Int) {
        progressBar.progress = percentage
    }


}