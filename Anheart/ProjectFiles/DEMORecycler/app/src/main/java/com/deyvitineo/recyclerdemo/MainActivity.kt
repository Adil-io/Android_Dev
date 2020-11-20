package com.deyvitineo.recyclerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val LIST_VIEW = "LIST_VIEW"
    private val GRID_VIEW = "GRID_VIEW"
    private lateinit var mCurrentVisibleView: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListView()
        //Floating action button listener
        fab.setOnClickListener {
            if (mCurrentVisibleView == LIST_VIEW) {
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_list))
                setupGridView()
            } else {
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_grid))
                setupListView()
            }
        }
    }

    //sets the recyclerview with a linear (list) layout manager
    private fun setupListView() {
        mCurrentVisibleView = LIST_VIEW
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter(this, getItems())
        recyclerView.adapter = itemAdapter
    }

    //sets the recyclerview with a grid layout manager
    private fun setupGridView() {
        mCurrentVisibleView = GRID_VIEW
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val itemAdapter = ItemAdapter(this, getItems())
        recyclerView.adapter = itemAdapter
    }

    /**
     * Generates an array list <String> which contains the amount of values as the for loop goes for.
     * Play around with the numbers to create difference size lists and seeing how recyclerview works
     */
    private fun getItems(): ArrayList<String> {
        var items = ArrayList<String>()

        for (number in 1..30) {
            items.add("Item$number")
        }

        return items
    }
}