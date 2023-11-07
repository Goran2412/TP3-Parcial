package com.example.parcialtp3.ui.filter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import com.example.parcialtp3.R
import java.util.Arrays

class CheckboxListAdapter(private val context: Context, private val items: List<String>) : BaseAdapter() {

    private val checkedItems = BooleanArray(items.size)

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Define an interface for item selection events
    interface OnItemSelectionListener {
        fun onItemSelectionChanged(item: String, isSelected: Boolean)
    }

    // Create a variable to hold the listener
    private var itemSelectionListener: OnItemSelectionListener? = null

    // Setter method for the listener
    fun setOnItemSelectionListener(listener: OnItemSelectionListener) {
        this.itemSelectionListener = listener
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position) as String
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.checkbox_list_item, null)

        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = item
        checkBox.isChecked = checkedItems[position]

//        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
//            checkedItems[position] = isChecked
//        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            Log.d("CheckboxListAdapter", "Item at position $position is checked: $isChecked. Text :${checkBox.text}")
            // You can notify the change to the parent fragment/activity if needed
        }
        return itemView
    }
}

data class FilterItem(val category: String, val itemText: String)