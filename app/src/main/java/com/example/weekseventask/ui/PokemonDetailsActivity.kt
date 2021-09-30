package com.example.weekseventask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weekseventask.model.PokemonDetailClass
import com.example.weekseventask.R
import com.example.weekseventask.ViewModel.DetailActivityViewModel
import com.squareup.picasso.Picasso
import java.net.UnknownHostException

class PokemonDetailsActivity : AppCompatActivity() {
//    lateinit var idNumber: String
    lateinit var pokemonImage: ImageView
    lateinit var name: TextView
    lateinit var ability: TextView
    lateinit var height: TextView
    lateinit var weight: TextView
    lateinit var specie: TextView
    lateinit var pokemonDetailsTitle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val idNumber = intent.getStringExtra("Extra_Data")
        pokemonImage = findViewById(R.id.imageView)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idNumber}.png")
            .into(pokemonImage)

        initViewModel(idNumber.toString())

    }


    private fun initViewModel(idNumber :String) {
        val viewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)
        viewModel.pokemonDetailLiveData.observe(this, Observer<PokemonDetailClass> {
            if(it != null){
                boundItem(it)
            } else{
                Toast.makeText(this,"error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        try{
            viewModel.makeApiCall(idNumber)

        } catch (e: UnknownHostException){
            Toast.makeText(this, "No internet connection. Please turn on internet", Toast.LENGTH_LONG).show()
        }

    }

    private fun boundItem(pokemonDetailClass: PokemonDetailClass){
        name = findViewById(R.id.name)
        ability = findViewById(R.id.ability)
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
        specie = findViewById(R.id.specie)
        pokemonDetailsTitle = findViewById(R.id.pokemonDetailsTitle)
        ability.text = String.format("Ability :${pokemonDetailClass.abilities[0].ability.name}")
        height.text = String.format("Height : ${pokemonDetailClass.height}")
        pokemonDetailsTitle.text = String.format("${pokemonDetailClass.species.name} Detail Page")
        name.text = String.format("Name :${pokemonDetailClass.name}")
        weight.text = String.format("Weight :${pokemonDetailClass.weight}")
        specie.text = String.format("Moves : ${pokemonDetailClass.moves[0].move.name}, ${pokemonDetailClass.moves[1].move.name}")




    }
}