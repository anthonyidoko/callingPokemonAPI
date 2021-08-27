package com.example.fetchingapidata.viewModel

import android.content.Intent
import android.content.Intent.getIntent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchingapidata.data.PokemonData
import com.example.fetchingapidata.network.RetrofitClient
import com.example.fetchingapidata.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {

    private var pokemonDataMutableLiveData = MutableLiveData<PokemonData>()
    val pokemonDataLiveData :LiveData<PokemonData>  = pokemonDataMutableLiveData


    fun makeAPICall() {

        viewModelScope.launch (Dispatchers.IO){

//            val retrofitInstance = RetrofitClient.getRetrofitInstance()
//                .create(RetrofitService::class.java)
            val response = RetrofitClient.getRetrofitInstance().getPokemonFromRange(8,0)
            pokemonDataMutableLiveData.postValue(response)

        }
    }
}