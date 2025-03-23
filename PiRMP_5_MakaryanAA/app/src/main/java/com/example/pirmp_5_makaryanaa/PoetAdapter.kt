package com.example.pirmp_5_makaryanaa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoetAdapter(private val authors: List<AuthorPoems>, private val onClick: (AuthorPoems) -> Unit) :
    RecyclerView.Adapter<PoetAdapter.PoetViewHolder>(){
    class PoetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return PoetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PoetViewHolder, position: Int) {
        holder.nameTextView.text = authors[position].name
        holder.nameTextView.setOnClickListener{onClick(authors[position])}
    }

    override fun getItemCount(): Int = authors.size

}