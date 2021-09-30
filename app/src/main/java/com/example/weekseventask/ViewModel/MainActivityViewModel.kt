package com.example.networkcall.ViewModel


import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcall.Network.RetroService
import com.example.networkcall.Network.RetroInstance
import com.example.weekseventask.model.PokemonDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException


class MainActivityViewModel :ViewModel() {

    private var recyclerListLiveData :MutableLiveData<PokemonDataClass> = MutableLiveData()

    fun getRecyclerListObserver() :MutableLiveData<PokemonDataClass>{

        return recyclerListLiveData
    }

    // Call Data from the endpoint
    fun makeApiCall(){


            viewModelScope.launch(Dispatchers.IO) {
                try {
                val retroInstance =  RetroInstance.getRetroInstance()
                val res = retroInstance.getAllDataFromApi(limit= 1118, offset = 0)

                Log.d("Viewmodel", res.toString())
                recyclerListLiveData.postValue(res)
                }catch (e: UnknownHostException){
                    e.printStackTrace()
                }

            }


    }
}


