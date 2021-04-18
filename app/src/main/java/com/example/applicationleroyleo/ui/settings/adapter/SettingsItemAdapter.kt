package com.example.applicationleroyleo.ui.settings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationleroyleo.R
import com.example.applicationleroyleo.data.SettingsItem

class SettingsItemAdapter(private val settingItemList: Array<SettingsItem>, private val onClick: ((selectedItem: SettingsItem) -> Unit)? = null) : RecyclerView.Adapter<SettingsItemAdapter.ViewHolder>() {

    // Comment s'affiche ma vue
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun showItem(settingItem: SettingsItem, onClick: ((selectedItem: SettingsItem) -> Unit)? = null) {
            itemView.findViewById<TextView>(R.id.item_text).text = settingItem.name
            itemView.findViewById<ImageView>(R.id.item_image).setImageResource(settingItem.icon)
            itemView.findViewById<ConstraintLayout>(R.id.constraint_itemList).setOnClickListener() {
                settingItem.onClick()
            }

            if(onClick != null) {
                itemView.setOnClickListener {
                    settingItem.onClick()
                }
            }
        }
    }

    // Retourne une « vue » / « layout » pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    // Connect la vue ET la données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.showItem(settingItemList[position], onClick)
    }

    override fun getItemCount(): Int {
        return settingItemList.size
    }

}