package com.example.weekseventask.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcall.Adapter.RecyclerViewAdapter
import com.example.networkcall.ViewModel.MainActivityViewModel
import com.example.weekseventask.model.PokemonDataClass
import com.example.weekseventask.R
import com.example.weekseventask.connectivity.ConnectivityLiveData
import java.net.UnknownHostException


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListFragment() : Fragment() {

    private lateinit var recyclerAdapter :RecyclerViewAdapter
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var recycler :RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_recycler_list, container, false)
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        recycler = view.findViewById(R.id.recyclerView)


        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(){

            val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<PokemonDataClass> {
                if(it != null){
                    recyclerAdapter.setUpdateData(it.results)
                } else{
                    Toast.makeText(activity,"error in getting data", Toast.LENGTH_SHORT).show()
                }
            })

            viewModel.makeApiCall()
        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            when (isAvailable) {
                true -> {
                    recycler.visibility = View.VISIBLE
                }
                false -> {
                    recycler.visibility = View.GONE
                }
            }
        })




    }

    private fun initViewModel(view :View){
        //Initialize recyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter

        //Set click listener on pokemon and navigate to detail activity
        recyclerAdapter.setOnPokemonClickListener(object : RecyclerViewAdapter.SetPokemonClickListener{
            override fun showItemDetails(position: Int, myView: View?) {
                val pokemonId = position + 1
                val intent = Intent(activity,PokemonDetailsActivity::class.java)
                intent.putExtra("Extra_Data",pokemonId.toString())
                startActivity(intent)
            }

        })

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }


}