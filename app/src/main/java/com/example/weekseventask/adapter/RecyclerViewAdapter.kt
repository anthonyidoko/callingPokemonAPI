package com.example.networkcall.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weekseventask.model.Result
import com.example.weekseventask.R
import com.squareup.picasso.Picasso



class RecyclerViewAdapter() :RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items  = ArrayList<Result>()
    lateinit var listener :SetPokemonClickListener

    fun setUpdateData(items :ArrayList<Result>){
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view : View,listener :SetPokemonClickListener) :RecyclerView.ViewHolder(view){

        val myImage :ImageView = view.findViewById(R.id.myImage)
        val tvTitle :TextView = view.findViewById(R.id.tvTitle)
        val imageUrl:TextView = view.findViewById(R.id.imageUrl)


        init {
            itemView.setOnClickListener{
                listener.showItemDetails(adapterPosition,itemView)
            }
        }

        fun bind(data :Result){
            tvTitle.text = data.name
            val imageNumber = data.getImageUrlNumber()
            val imageUrl = data.url
            Picasso.get()
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${imageNumber}.png")
                .into(myImage)
        }

    }

    interface SetPokemonClickListener{
        fun showItemDetails(position: Int, myView :View?)
    }

    fun setOnPokemonClickListener(pokeListener: SetPokemonClickListener){
        listener = pokeListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)
        return MyViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}