package com.prodev.bigbang.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prodev.bigbang.R
import com.prodev.bigbang.model.Beer
import kotlinx.android.synthetic.main.beer_item.view.*

class MainAdapter(private val beer: ArrayList<Beer>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(beer: Beer) {
            itemView.apply {
                tv_beer_name.text = beer.name
                //tv_beer_tip.text = beer.brewers_tips
                Glide.with(iv_beer_image.context)
                    .load(beer.image_url)
                    .into(iv_beer_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_item, parent, false)
        )

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) =
        holder.bind(beer[position])

    override fun getItemCount(): Int = beer.size

    fun addBeers(beers: List<Beer>) {
        this.beer.apply {
            clear()
            addAll(beers)
        }
    }
}




