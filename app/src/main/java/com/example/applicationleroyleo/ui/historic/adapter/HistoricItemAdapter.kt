package com.example.applicationleroyleo.ui.historic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationleroyleo.R

class HistoricItemAdapter(private val historicItemList: MutableSet<String>?) : RecyclerView.Adapter<HistoricItemAdapter.ViewHolder>() {

    // Comment s'affiche ma vue
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(historicItem: String?) {
            itemView.findViewById<TextView>(R.id.string_text).text = historicItem.toString()
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.string_list, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (historicItemList != null) {
            holder.showItem(historicItemList.elementAt(position))
        }
    }

    override fun getItemCount(): Int {
        if (historicItemList != null) {
            return historicItemList.count()
        }
        return 0
    }
}