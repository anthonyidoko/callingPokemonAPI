package com.example.fetchingapidata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchingapidata.adapter.PokemonAdapter
import com.example.fetchingapidata.data.PokemonData
import com.example.fetchingapidata.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var limit :EditText
    lateinit var offset :EditText
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        limit = findViewById(R.id.limit)
        offset  = findViewById(R.id.offset)
        val btnFetch :Button = findViewById(R.id.btnFetch)
        recyclerView = findViewById(R.id.myRecyclerView)

        btnFetch.setOnClickListener {
            initViewModel(
                limit.text.toString().toInt(),
                offset.text.toString().toInt()-1
            )
        }

        recyclerView.layoutManager = GridLayoutManager(this,2)
        val decoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)





    }

    //Initialize viewMode
    fun initViewModel(limit :Int, offset :Int){
        //my viewModel Object
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.pokemonDataLiveData.observe(this, Observer<PokemonData> {
            if (it != null) {
                recyclerView.adapter = PokemonAdapter(it.results)
            } else {
                Toast.makeText(this, "No data for now", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeAPICall(limit, offset)

    }




}