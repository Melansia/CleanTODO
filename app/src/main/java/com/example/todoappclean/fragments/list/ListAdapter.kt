package com.example.todoappclean.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappclean.R
import com.example.todoappclean.data.models.Priority
import com.example.todoappclean.data.models.ToDoData

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tvRowLTitleText = holder.itemView.findViewById<TextView>(R.id.tvRowLTitleText)
        val tvRowLDescription = holder.itemView.findViewById<TextView>(R.id.tvRowLDescription)
        val cvRowLPriorityIndicator =
            holder.itemView.findViewById<CardView>(R.id.cvRowLPriorityIndicator)
        val priority = dataList[position].priority

        tvRowLTitleText.text = dataList[position].title
        tvRowLDescription.text = dataList[position].descriptions
        when (priority) {
            Priority.HIGH -> cvRowLPriorityIndicator.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> cvRowLPriorityIndicator.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow
                )
            )
            Priority.LOW -> cvRowLPriorityIndicator.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
        }
    }

    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}