package com.example.weekseventask.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcall.Network.RetroInstance
import com.example.networkcall.Network.RetroService
import com.example.weekseventask.model.PokemonDetailClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class DetailActivityViewModel : ViewModel() {

    var pokemonDetailLiveData :MutableLiveData<PokemonDetailClass> = MutableLiveData()

//    fun getDetailListObserver() : MutableLiveData<PokemonDetailClass> {
//
//        return pokemonDetailLiveData
//    }

    fun makeApiCall(id :String){
        viewModelScope.launch(Dispatchers.IO) {

            try{
                val retroInstance =  RetroInstance.getRetroInstance()

                val res = retroInstance.getPokemonDetailFromApi(id)

                Log.d("Viewmodel", res.toString())
                pokemonDetailLiveData.postValue(res)
            }catch (e: UnknownHostException){
                e.printStackTrace()
            }

        }

    }
}
