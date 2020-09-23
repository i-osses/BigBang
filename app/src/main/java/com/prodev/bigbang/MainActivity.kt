package com.prodev.bigbang

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodev.bigbang.model.Beer
import com.prodev.bigbang.model.adapter.MainAdapter
import com.prodev.bigbang.model.db.BeerDatabase
import com.prodev.bigbang.model.repository.BeerRepository
import com.prodev.bigbang.model.util.Status.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.beer_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = BeerDatabase.getInstance(application).beerDAO
        val repository = BeerRepository(dao)
        val factory = MainViewModelFactory(repository)

        mViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setupUI()
        setupObserver()



    }


/*    private fun display() {
        mViewModel.beer.observe(this, Observer {
            Log.i("MYTAG", it.toString())

        })
    }

    private fun loadDB() {
        mViewModel.myResponse.observe(this, Observer {
            Log.i("MYTAG2", it.toString())
        })
    }*/

    private fun setupUI() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mViewModel.getBeerRF().observe(this, Observer {

            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { beers -> retrieverList(beers) }
                        Log.i("TAG", "SUCCESS")
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.i("TAG", "ERROR")
                    }
                    LOADING -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        Log.i("TAG", "LOADING")
                    }
                }
            }
        })
    }

    private fun retrieverList(beers: List<Beer>) {
        adapter.apply {
            addBeers(beers)
            notifyDataSetChanged()
        }

    }
}