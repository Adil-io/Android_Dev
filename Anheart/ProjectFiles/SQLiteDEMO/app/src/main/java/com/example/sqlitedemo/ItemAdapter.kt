package com.example.sqlitedemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_row.view.*

class ItemAdapter(private val context: Context, private val items: ArrayList<EmpModelClass>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.items_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name
        holder.tvEmail.text = item.email

        if(position % 2 == 0){
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.LightGrey
                )
            )
        }
        else{
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.White
                )
            )
        }

        holder.ivEdit.setOnClickListener {
            if(context is MainActivity){
                context.updateRecordDialog(item)
            }
        }

        holder.ivDelete.setOnClickListener {
            if(context is MainActivity){
                context.deleteRecordAlertDialog(item)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val llMain: LinearLayout = view.llMain
        val tvName: TextView = view.tvName
        val tvEmail: TextView = view.tvEmail
        val ivEdit: ImageView = view.ivEdit
        val ivDelete: ImageView = view.ivDelete
    }
}