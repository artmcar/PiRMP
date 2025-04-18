package com.example.pirmp_9_makaryanaa

import android.app.AlertDialog
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<EditText>(R.id.editText_name)
        val contentText = findViewById<EditText>(R.id.editText_content)
        val button_save = findViewById<Button>(R.id.button_save)
        val button_add = findViewById<Button>(R.id.button_add)
        val button_delete = findViewById<Button>(R.id.button_delete)
        val button_read = findViewById<Button>(R.id.button_read)
        textView = findViewById<TextView>(R.id.textView)

        val storageDir : File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)


        if (savedInstanceState != null){
            textView.text = savedInstanceState.getString("KEY_STATE")
        }

        button_save.setOnClickListener {
            //Создание и запись файла
            if (!storageDir.exists()){
                Toast.makeText(this,"Директории не существует", Toast.LENGTH_LONG).show()
                //storageDir.mkdirs()
            }
            val file = File(storageDir, nameText.text.toString())
            val name = nameText.text.toString()
            try {
                if (file.exists()){
                    Toast.makeText(this,"Файл с таким именем уже существует", Toast.LENGTH_LONG).show()
                }
                else{
                    val created = file.createNewFile()
                    if(created){
                        FileWriter(file).use {writer ->
                            writer.append(contentText.text.toString())
                            writer.flush()
                        }
                        Toast.makeText(this,"Файл $name создан", Toast.LENGTH_LONG).show()

                    }
                }
            } catch (e : IOException){
                e.printStackTrace()
            }

        }

        button_read.setOnClickListener {
            //Чтение файла
            val file = File(storageDir, nameText.text.toString())
            if (file.exists()){
                val text = StringBuilder()

                try {
                    BufferedReader(FileReader(file)).use { br ->
                        var line: String?
                        while (br.readLine().also { line = it } != null) {
                            text.append(line)
                            text.append('\n')
                        }
                    }
                } catch (e : IOException){
                    e.printStackTrace()
                }
                textView.text = text.toString()
            }
        }

        button_delete.setOnClickListener {
            //Удаление файла
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Подтверждение")
            builder.setMessage("Вы уверены, что хотите удалить этот файл?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            // Установка кнопки "Да" и ее обработчика
            builder.setPositiveButton("Да") { _, _ ->
                // Обработка подтверждения
                val file = File(storageDir, nameText.text.toString())

                if(file.exists()){
                    val deleted = file.delete()
                    if(deleted){
                        Toast.makeText(this,"Файл был удален", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,"Ой, файл не был удален", Toast.LENGTH_LONG).show()
                    }
                }
            }

            // Установка кнопки "Отмена" и ее обработчика
            builder.setNegativeButton("Отмена") { dialog, _ ->
                // Обработка отмены действия
                dialog.dismiss()
            }

            // Создание и отображение AlertDialog
            val dialog = builder.create()
            dialog.show()

        }

        button_add.setOnClickListener {
            //Добавление в файл
            val file = File(storageDir, nameText.text.toString())
            val name = nameText.text.toString()
            if (file.exists()){
                FileWriter(file, true).use {writer ->
                    writer.append(contentText.text.toString())
                    writer.flush()
                }
                Toast.makeText(this,"Текст добавлен в файл $name", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this,"Такого файла не существует", Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("KEY_STATE", textView.text.toString())
        super.onSaveInstanceState(outState)
    }

}