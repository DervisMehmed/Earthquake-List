package com.example.earthquakenotifier.ui.mainscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakenotifier.Model.Earthquake
import com.example.earthquakenotifier.R
import com.example.earthquakenotifier.ui.mainscreen.quakeInfoScreen.LocationInfoScreen

class CustomRecyclerViewAdapter(private val activity: Context?, private val arrData: List<Earthquake>)
    : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView: View? = null
        itemView = LayoutInflater.from(activity).inflate(R.layout.quake_card_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val model = arrData[i]
        holder.bindCard(model)
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val quakeDate : TextView = view.findViewById(R.id.textViewDate)
        val quakeDepth: TextView = view.findViewById(R.id.textViewDepth)
        val quakeMagnitude: TextView = view.findViewById(R.id.textViewMagnitude)
        val quakePlace: TextView = view.findViewById(R.id.textViewPlace)
        val quakeCircle: ImageView = view.findViewById(R.id.imageViewLocMag)
        var earthquake: Earthquake? = null

        @SuppressLint("SetTextI18n")
        fun bindCard(quake : Earthquake){
            this.earthquake = quake
            quakeDate.text = quake.date
            quakeDepth.text = quake.depth.toString() + " km"
            quakeMagnitude.text = quake.ml
            quakePlace.text = quake.place
            with(quakeCircle) {
                when((quake.ml.toDouble()).toInt()){
                    0, 1 -> setImageResource(R.drawable.circle0)
                    2 -> setImageResource(R.drawable.circle1)
                    3 -> setImageResource(R.drawable.circle2)
                    4 -> setImageResource(R.drawable.circle3)
                    5 -> setImageResource(R.drawable.circle4)
                    6 -> setImageResource(R.drawable.circle5)
                    7 -> setImageResource(R.drawable.circle6)
                    8 -> setImageResource(R.drawable.circle7)
                    9 -> setImageResource(R.drawable.circle8)
                    10-> setImageResource(R.drawable.circle9)
                }
            }
        }

        init {
            view.setOnClickListener(this)
            }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, LocationInfoScreen::class.java)
            intent.putExtra("QUAKECARD", earthquake)
            context.startActivity(intent)
        }
    }
}
