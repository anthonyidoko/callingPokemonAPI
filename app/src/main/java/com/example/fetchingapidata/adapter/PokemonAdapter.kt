package com.example.fetchingapidata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchingapidata.R
import com.example.fetchingapidata.data.Result

class PokemonAdapter(val pokemonItems: List<Result>) : RecyclerView.Adapter<PokemonAdapter.MyViewholder>(){


    class MyViewholder(view : View) :RecyclerView.ViewHolder(view){
        val pokemonName :TextView = view.findViewById(R.id.pokemonName)
        val pokemonImage :ImageView = view.findViewById(R.id.pokemonImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.pokemon_list,parent,false)
        return MyViewholder(view)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val currentPokemon = pokemonItems[position]
        holder.pokemonName.text = currentPokemon.name
    }

    override fun getItemCount() = pokemonItems.size
}