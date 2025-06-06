package com.pirmp.pirmp_12_makaryanaa

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //p1
        val contentUri = Uri.parse("content://com.example.pirmp_10_makaryanaa.provider/authors")

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val result = StringBuilder()

        val cursor = contentResolver.query(
            contentUri,
            null,
            null,
            null,
            null
        )

        if (cursor == null || cursor.count == 0) {
            tvResult.text = "Не найдено ${cursor?.count}"
        } else {
            cursor.use {
                val nameColumn = it.getColumnIndex("author")

                while (it.moveToNext()) {
                    val name = it.getString(nameColumn)
                    result.appendLine("Author: $name")
                    Log.d("DDD", name)
                    Log.d("DDD", "Cursor count: ${cursor.count}")
                }
            }
            tvResult.text = result.toString()
        }


        //p2
        val user_json = createJsonUsingGson()
        saveToFile("user.json", user_json)

        val user_array_json = createJsonArrayUsingGson()
        saveToFile("users.json", user_array_json)

        val read_user_json = readFromFile("user.json")
        parseJsonUsingGson(read_user_json)

        val read_user_array_json = readFromFile("users.json")
        parseJsonArrayUsingGson(read_user_array_json)
    }

    private fun createJsonUsingGson(): String {
        val user = User()
        user.name = "Alice"
        user.age = 25
        user.email = "alice@example.com"

        return gson.toJson(user)
    }

    private fun createJsonArrayUsingGson(): String {
        val users = listOf(
            User("John", 30, "john@example.com"),
            User("Alice", 25, "alice@example.com")
        )
        return gson.toJson(users)
    }

    private fun parseJsonUsingGson(jsonStr: String) {
        val user = gson.fromJson(jsonStr, User::class.java)
        Log.d("JSON", "Single User from File:")
        Log.d("JSON", "Name: ${user.name}")
        Log.d("JSON", "Age: ${user.age}")
        Log.d("JSON", "Email: ${user.email}")
    }

    private fun parseJsonArrayUsingGson(jsonArrayStr: String) {
        val userListType = object : TypeToken<List<User>>() {}.type
        val users: List<User> = gson.fromJson(jsonArrayStr, userListType)

        Log.d("JSON", "User List from File:")
        for (user in users) {
            Log.d("JSON", "Name: ${user.name}")
            Log.d("JSON", "Age: ${user.age}")
            Log.d("JSON", "Email: ${user.email}")
        }
    }

    private fun saveToFile(fileName: String, content: String) {
        openFileOutput(fileName, MODE_PRIVATE).use {
            it.write(content.toByteArray())
        }
        Log.d("FILE", "Saved to $fileName:\n$content")
    }

    private fun readFromFile(fileName: String): String {
        return openFileInput(fileName).bufferedReader().use { it.readText() }
    }

}