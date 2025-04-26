package com.example.pirmp_10_makaryanaa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pirmp_10_makaryanaa.db.PoemAuthor

class PoemAuthorAdapter(private val poemAuthors: List<PoemAuthor>) : RecyclerView.Adapter<PoemAuthorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val authorText: TextView = view.findViewById(R.id.text_author)
        private val poemText: TextView = view.findViewById(R.id.text_poem)

        fun bind(poemAuthor: PoemAuthor) {
            authorText.text = poemAuthor.author
            poemText.text = poemAuthor.poem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poemauthor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(poemAuthors[position])
    }

    override fun getItemCount(): Int {
        return poemAuthors.size
    }
}