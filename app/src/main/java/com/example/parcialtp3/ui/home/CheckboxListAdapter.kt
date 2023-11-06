package com.example.parcialtp3.ui.home

import android.content.Context
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position) as String
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.checkbox_list_item, null)

        val checkBox = itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = item
        checkBox.isChecked = checkedItems[position]

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            checkedItems[position] = isChecked
        }

        return itemView
    }

    fun getCheckedItems(): List<String> {
        val selectedItems = mutableListOf<String>()
        for (i in items.indices) {
            if (checkedItems[i]) {
                selectedItems.add(items[i])
            }
        }
        return selectedItems
    }

    fun resetCheckedItems() {
        Arrays.fill(checkedItems, false)
    }
}