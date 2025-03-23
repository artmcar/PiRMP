package com.example.pirmp_5_makaryanaa

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PoemsActivity : AppCompatActivity() {
    private lateinit var author: AuthorPoems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poems)

        author = intent.getSerializableExtra("author") as AuthorPoems
        findViewById<TextView>(R.id.textViewAuthor).text = author.name

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewPoems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PoemAdapter(author.poems.toMutableList(), { poem ->
            val intent = Intent(this, ReadPoemActivity::class.java)
            intent.putExtra("poem", poem)
            startActivity(intent)
        }, { poem ->
            val updatedPoems = author.poems.toMutableList().apply { remove(poem) }
            author = author.copy(poems = updatedPoems)
            recyclerView.adapter = PoemAdapter(updatedPoems, { selectedPoem ->
                val intent = Intent(this, ReadPoemActivity::class.java)
                intent.putExtra("poem", selectedPoem)
                startActivity(intent)
            }, this::onPoemDelete)
        })

    }
    private fun onPoemDelete(poem: String) {
        author = author.copy(poems = author.poems.filter { it != poem })
        recreate()
    }
}