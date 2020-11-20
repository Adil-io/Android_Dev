package com.deyvitineo.recyclerdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

class ItemAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /**
     * Inflates the layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_recycler_item, parent, false)
        )
    }

    //returns the size of the array within the recyclerview
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     *Gets called every time a new view is visible. so whenever you scroll or when you start the app
     *this will be called for each item.
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemValue = items[position]
        holder.itemTextView.text = itemValue

        if (position % 2 == 0) {
            holder.itemTextView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorLightGray
                )
            )
        } else {
            holder.itemTextView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            )
        }
    }

    /**
     * View Holder class (necessary). It holds all the views within the layout you are inflating
     * above. EX: it holds the textviews, imageviews, buttons, etc
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView = itemView.tvItem
    }

}