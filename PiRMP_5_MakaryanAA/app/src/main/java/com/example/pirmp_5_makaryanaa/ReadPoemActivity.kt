package com.example.pirmp_5_makaryanaa

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ReadPoemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_poem)

        val poem = intent.getStringExtra("poem") ?: ""
        val poemTextResId = when (poem) {
            "Annabel Lee" -> R.string.anabel_lee
            "Eldorado" -> R.string.eldorado
            "The Raven" -> R.string.the_raven
            "День Великой Победы" -> R.string.dvp
            "Наши историки и наши гусаны" -> R.string.niing
            "За цветами в зимний сад" -> R.string.zcvzs
            "Мы очищали старый сад" -> R.string.moss
            "Я пришел к тебе с приветом" -> R.string.ypktsp
            "Чудная картина" -> R.string.ck
            "А мы с тобой, брат, из пехоты" -> R.string.amsbip
            "Осудите сначала себя самого" -> R.string.osss
            "Мне кажется, я жил давно" -> R.string.mkyjd
            "Буря с берега" -> R.string.bsb
            "Юному поэту" -> R.string.yp
            "Песня о друге" -> R.string.pod
            "Жди меня, и я вернусь" -> R.string.jmiyv
            "Открытое письмо" -> R.string.op
            "Мне нравится, что Вы больны не мной" -> R.string.mncvbnm
            "Ветер кружит желтой тучею" -> R.string.vkjt
            "Нет в этой жизни рубежей" -> R.string.nvajr
            "Конь" -> R.string.kon
            "Кто понял жизнь тот больше не спешит" -> R.string.kpjtbns
            "Не делай зла - вернется бумерангом" -> R.string.ndzvb
            "Серые глаза - рассвет" -> R.string.sgr
            "Двое" -> R.string.dvoe
            "Общий итог" -> R.string.oi
            "Жалобы" -> R.string.zhalobi
            "Разрушение" -> R.string.razrushenie
            "Ինչու հիշեցի" -> R.string.ih
            "Քարը" -> R.string.qary
            "Անմեղ-մեղավոր" -> R.string.anmmex
            "Երևան-Էրեբունի" -> R.string.yvnybn
            "Մարդ էլ կա, մարդ էլ" -> R.string.mekme
            "Մոր ձեռքերը" -> R.string.mdz
            "Որդուս" -> R.string.vordus
            "Սարդարապատ" -> R.string.sdrpt
            "Քիչ ենք, բայց հայ ենք" -> R.string.qebhe
            "Ծիրանի ծառ" -> R.string.cc
            "Շատ ուշացավ" -> R.string.shush
            "Սասունասար" -> R.string.ssns

            else -> R.string.warning
        }
        findViewById<TextView>(R.id.textViewPoemTitle).text = poem
        findViewById<TextView>(R.id.textViewPoemContent).text = getString(poemTextResId)

    }
}