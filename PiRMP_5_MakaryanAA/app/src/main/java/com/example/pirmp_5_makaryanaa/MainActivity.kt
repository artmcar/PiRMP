package com.example.pirmp_5_makaryanaa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       val authors = listOf(
           AuthorPoems(1, "Edgar Allan Poe", listOf("Annabel Lee", "Eldorado", "The Raven")),
           AuthorPoems(2, "Аветик Исаакян", listOf("День Великой Победы", "Наши историки и наши гусаны")),
           AuthorPoems(3, "Агния Барто", listOf("За цветами в зимний сад", "Мы очищали старый сад")),
           AuthorPoems(4, "Афанасий Фет", listOf("Я пришел к тебе с приветом", "Тополь", "Чудная картина")),
           AuthorPoems(5, "Булат Окуджава", listOf("А мы с тобой, брат, из пехоты", "Осудите сначала себя самого")),
           AuthorPoems(6, "Ваган Текеян", listOf("Мне кажется, я жил давно")),
           AuthorPoems(7, "Валерий Брюсов", listOf("Буря с берега", "Юному поэту")),
           AuthorPoems(8, "Владимир Высоцкий", listOf("Песня о друге")),
           AuthorPoems(9, "Константин Симонов", listOf("Жди меня, и я вернусь", "Открытое письмо")),
           AuthorPoems(10, "Марина Цветаева", listOf("Мне нравится, что Вы больны не мной")),
           AuthorPoems(11, "Маро Маркарян", listOf("Ветер кружит желтой тучею", "Нет в этой жизни рубежей")),
           AuthorPoems(12, "Николай Языков", listOf("Конь")),
           AuthorPoems(13, "Омар Хаям", listOf("Кто понял жизнь тот больше не спешит", "Не делай зла - вернется бумерангом")),
           AuthorPoems(14, "Редьярд Киплинг", listOf("Серые глаза - рассвет", "Двое", "Общий итог")),
           AuthorPoems(15, "Рубен Севак", listOf("Старый священник", "Хохот")),
           AuthorPoems(16, "Фрик", listOf("Жалобы")),
           AuthorPoems(17, "Шарль Бодлер", listOf("Разрушение")),
           AuthorPoems(18, "Համո Սահյան", listOf("Ինչու հիշեցի", "Քարը")),
           AuthorPoems(19, "Պարույր Սևակ", listOf("Անմեղ-մեղավոր", "Երևան-Էրեբունի", "Մարդ էլ կա, մարդ էլ", "Մոր ձեռքերը", "Որդուս", "Սարդարապատ", "Քիչ ենք, բայց հայ ենք")),
           AuthorPoems(20, "Ջիվանի", listOf("Ծիրանի ծառ", "Շատ ուշացավ", "Սասունասար")),
       )

       val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
       recyclerView.layoutManager = LinearLayoutManager(this)
       recyclerView.adapter = PoetAdapter(authors) {author ->
           val intent = Intent(this, PoemsActivity::class.java)
           intent.putExtra("author", author)
           startActivity(intent)
       }
    }
}