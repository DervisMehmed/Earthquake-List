package com.example.earthquakenotifier.ui.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R

class CardViewAdapter(var context: Context, var quakeList: List<Earthquake>) : BaseAdapter() {

    override fun getCount(): Int {
        return quakeList.size
    }

    override fun getItem(position: Int): Any {
        return quakeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.quake_card_view, null)
        val quakeDate : TextView = view.findViewById(R.id.textViewDate)
        val quakeDepth: TextView = view.findViewById(R.id.textViewDepth)
        val quakeMagnitude: TextView = view.findViewById(R.id.textViewMagnitude)
        val quakePlace: TextView = view.findViewById(R.id.textViewPlace)

        val quake: Earthquake = quakeList[position]

        quakeDate.text = quake.date
        quakeDepth.text = quake.depth.toString() + " km"
        quakeMagnitude.text = quake.ml
        quakePlace.text = quake.place
        return view
    }
}