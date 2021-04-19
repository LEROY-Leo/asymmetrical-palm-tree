package com.example.applicationleroyleo.ui.historic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationleroyleo.R

// this class is used to create the view of the historic recycler
class HistoricItemAdapter(private val historicItemList: MutableSet<String>?) : RecyclerView.Adapter<HistoricItemAdapter.ViewHolder>() {

    // link the data to change in every item of the recycler
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(historicItem: String?) {
            itemView.findViewById<TextView>(R.id.string_text).text = historicItem.toString()
        }
    }

    // return the view/layout of every item of the recycler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.string_list, parent, false)
        return ViewHolder(view)
    }

    // link the view and the data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (historicItemList != null) {
            holder.showItem(historicItemList.elementAt(position))
        }
    }

    // count the item of the recycler list
    override fun getItemCount(): Int {
        if (historicItemList != null) {
            return historicItemList.count()
        }
        return 0
    }
}