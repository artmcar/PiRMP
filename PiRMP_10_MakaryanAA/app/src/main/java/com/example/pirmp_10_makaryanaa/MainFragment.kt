package com.example.pirmp_10_makaryanaa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pirmp_10_makaryanaa.db.DatabaseHelper
import com.example.pirmp_10_makaryanaa.db.PoemAuthor


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button_settings = view.findViewById<Button>(R.id.button_settings)
        val controller = findNavController()
        button_settings.setOnClickListener { controller.navigate(R.id.sharedPreferencesFragment) }

        val saveButton = view.findViewById<Button>(R.id.button_save)
        val updateButton = view.findViewById<Button>(R.id.button_update)
        val deleteButton = view.findViewById<Button>(R.id.button_delete)
        val findButton = view.findViewById<Button>(R.id.button_find)
        val authorInput = view.findViewById<EditText>(R.id.editText_author)
        val poemInput = view.findViewById<EditText>(R.id.editText_poem)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val dbHelper = DatabaseHelper(requireContext())
        val poemAuthors = dbHelper.getAllPoemAuthors()
        val adapter = PoemAuthorAdapter(poemAuthors)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        //Прописываем логику для сохранения нового автора
        saveButton.setOnClickListener {
            val author = authorInput.text.toString()
            val poem = poemInput.text.toString()
            val content = "content"
            val date = 2025
            if (dbHelper.addPoemAuthor(PoemAuthor(0, author, poem, content, date))) {
                poemAuthors.add(PoemAuthor(0, author, poem, content, date))
                adapter.notifyItemInserted(poemAuthors.size - 1)
                Toast.makeText(requireContext(), "Contact saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to save contact", Toast.LENGTH_SHORT).show()
            }
        }
        //удаляем автора
        deleteButton.setOnClickListener {
            val poem = poemInput.text.toString()
            if (dbHelper.deletePoemAuthor(poem)) {
                val position = poemAuthors.indexOfFirst { it.poem == poem }
                if (position != -1) {
                    poemAuthors.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    Toast.makeText(requireContext(), "Author deleted successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Author not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Failed to delete author", Toast.LENGTH_SHORT).show()
            }
        }

        //ищем автора по стиху
        findButton.setOnClickListener {
            val poem = poemInput.text.toString()
            val foundPoemAuthor = dbHelper.findPoemAuthor(poem)
            if (foundPoemAuthor != null) {
                authorInput.setText(foundPoemAuthor.author)
                poemInput.setText(foundPoemAuthor.poem)
                Toast.makeText(requireContext(), "Contact found: ${foundPoemAuthor.author}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Contact not found", Toast.LENGTH_SHORT).show()
            }
        }

        //обновляем данные
        updateButton.setOnClickListener {
            val oldPoem = poemInput.text.toString()
            val newAuthor = authorInput.text.toString()
            val newPoem = poemInput.text.toString()
            if (dbHelper.updatePoemAuthor(oldPoem, newAuthor, newPoem)) {
                Toast.makeText(requireContext(), "Contact updated successfully!", Toast.LENGTH_SHORT).show()
                refreshPoemAuthorsList(dbHelper, poemAuthors, adapter, recyclerView)
            } else {
                Toast.makeText(requireContext(), "Failed to update contact", Toast.LENGTH_SHORT).show()
            }
        }


    }
    fun refreshPoemAuthorsList(dbHelper: DatabaseHelper, poemAuthors: MutableList<PoemAuthor>, adapter: PoemAuthorAdapter, recyclerView: RecyclerView) {
        poemAuthors.clear()
        poemAuthors.addAll(dbHelper.getAllPoemAuthors()) // Загружаем обновленный список
        adapter.notifyDataSetChanged()
    }

}