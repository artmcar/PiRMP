package com.example.pirmp_7_makaryanaa

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment



class MainActivity : AppCompatActivity() {

    private var hour: Int = 0
    private var minute: Int = 0
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_time = findViewById<Button>(R.id.timeButton)
        val btn_date = findViewById<Button>(R.id.dateButton)
        val btn_custom = findViewById<Button>(R.id.customButton)
        val timeTextView = findViewById<TextView>(R.id.textView2)
        val dateTextView = findViewById<TextView>(R.id.textView3)

        // Создание строителя диалоговых окон
        val builder = AlertDialog.Builder(this)

        // Установка заголовка и сообщения диалогового окна
        builder.setTitle("Подтверждение")
        builder.setMessage("Поменять картинку?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        // Установка кнопки "Да" и ее обработчика
        builder.setPositiveButton("Да") { _, _ ->
            // Обработка подтверждения
            val firstFragment = FirstFragment()
            setNewFragment(firstFragment)
        }

        // Установка кнопки "Отмена" и ее обработчика
        builder.setNegativeButton("Отмена") { dialog, _ ->
            // Обработка отмены действия
            dialog.dismiss()
        }

        // Создание и отображение AlertDialog
        val dialog = builder.create()
        dialog.show()


        btn_time.setOnClickListener{
            val timePickerDialog = TimePickerDialog( this,
                { _, hourOfDay, minuteOfDay ->
                    // Обработка выбранного времени
                    // Пример: установка времени в TextView
                    timeTextView.text = "Установленное время: $hourOfDay:$minuteOfDay"
                },
                hour, minute, true
            ) // Использование 24-часового формата

            timePickerDialog.show()
        }


        btn_date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Обработка выбранной даты
                    var selectedMonth1 = selectedMonth + 1
                    dateTextView.text = "Выбранная дата: $selectedDay.$selectedMonth1.$selectedYear"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        // Создание пользовательского диалога
        btn_custom.setOnClickListener{
            val dialog = Dialog(this)
            // Установка макета для диалогового окна
            dialog.setContentView(R.layout.custom_dialog)
            val button = dialog.findViewById<Button>(R.id.buttonincustom)
            button.setOnClickListener{
                Toast.makeText(getApplicationContext(), "Кнопка была нажата", Toast.LENGTH_SHORT).show();
            }
            // Отображение диалогового окна
            dialog.show()
        }



        // Запуск сервиса для воспроизведения музыки
        val startIntent = Intent(this, MusicService::class.java)
        startService(startIntent)
    }
    fun setNewFragment(fragment: Fragment) {
        val ftransaction = supportFragmentManager.beginTransaction()
        ftransaction.replace(R.id.frame_layout, fragment)
        ftransaction.addToBackStack(null)
        ftransaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Остановка сервиса и музыки при уничтожении активности
        val stopIntent = Intent(this, MusicService::class.java)
        stopService(stopIntent)
    }

}