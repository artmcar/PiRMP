package com.example.pirmp_5_makaryanaa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoemAdapter (private val poems: MutableList<String>,
                   private val onReadClick: (String) -> Unit,
                   private val onDeleteClick: (String) -> Unit) :
    RecyclerView.Adapter<PoemAdapter.PoemViewHolder>() {
        class PoemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val textViewPoemTitle: TextView = itemView.findViewById(R.id.textViewPoemTitle)
            val buttonRead: Button = itemView.findViewById(R.id.buttonRead)
            val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poem_item, parent, false)
        return PoemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoemViewHolder, position: Int) {
        val poemTitle = poems[position]

        holder.textViewPoemTitle.text = poemTitle
        holder.buttonRead.setOnClickListener { onReadClick(poemTitle)}
        holder.buttonDelete.setOnClickListener{
            poems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,poems.size)
        }
    }

    override fun getItemCount(): Int = poems.size
}