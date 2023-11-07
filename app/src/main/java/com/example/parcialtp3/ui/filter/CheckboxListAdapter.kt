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

class CheckboxListAdapter(private val context: Context, private val items: List<CategorizedItem>) : BaseAdapter() {
    private val checkedItems = BooleanArray(items.size)
    private val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

    init {
        loadSavedSelection()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position) as CategorizedItem
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.checkbox_list_item, null)

        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = item.item
        checkBox.isChecked = checkedItems[position]

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            checkedItems[position] = isChecked
            saveSelectedItems()
            Log.d("CheckboxListAdapter", "Category: ${item.category}, Item: ${item.item} is checked: $isChecked")
        }
        return itemView
    }

    private fun saveSelectedItems() {
        val editor = sharedPreferences.edit()
        for (i in items.indices) {
            val key = "selected_$i"
            editor.putBoolean(key, checkedItems[i])
        }
        editor.apply()
    }

    private fun loadSavedSelection() {
        for (i in items.indices) {
            val key = "selected_$i"
            checkedItems[i] = sharedPreferences.getBoolean(key, false)
        }
    }
}

