package com.example.pirmp_10_makaryanaa

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class SharedPreferencesFragment : Fragment() {


    lateinit var sharedPreferences : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_shared_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextName : EditText = view.findViewById(R.id.editText_name)
        val button_write : Button = view.findViewById(R.id.button_write)
        val button_get : Button = view.findViewById(R.id.button_get)
        val button_delete : Button = view.findViewById(R.id.button_delete)
        val textViewName : TextView = view.findViewById(R.id.textView_name)

        sharedPreferences = activity?.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)!!

        //Запись
        button_write.setOnClickListener {

            var editor = sharedPreferences.edit()
            val name = editTextName.text.toString()
            editor?.putString("username", name)
            editor?.apply()
        }

        //Получение
        button_get.setOnClickListener {
            var username = sharedPreferences.getString("username", "defaultUsername")
            textViewName.text = username.toString()
        }

        //Удаление
        button_delete.setOnClickListener {
            var editor2 = sharedPreferences.edit()
            editor2?.clear()
            editor2?.apply()
        }




    }

}