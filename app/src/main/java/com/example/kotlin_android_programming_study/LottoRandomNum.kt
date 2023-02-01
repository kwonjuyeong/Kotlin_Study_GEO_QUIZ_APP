package com.example.kotlin_android_programming_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

//심심해서 5분만에 만든 로또 번호 판독기.. 퇴근하고 심심해서 만들어보아따.. 책 공부 해야하는데 시간은 안되고 그냥 심심해서...

class LottoRandomNum : AppCompatActivity() {
    private lateinit var sibal : Button
    private lateinit var year: TextView
    private lateinit var num1: TextView
    private lateinit var num2: TextView
    private lateinit var num3: TextView
    private lateinit var num4: TextView
    private lateinit var num5: TextView
    private lateinit var num6: TextView
    private lateinit var clear : Button
    private var result = 100000.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_random_num)
        val year4 = 1460
        for(i in 0 until year4){
            result *= 1.01
            val t_dec_up = DecimalFormat("#,###")
            Log.d("dataloggg", "원금 10만원 1년동안 매일 1%씩 수익 : ${t_dec_up.format(result.toBigDecimal())}원")

        }
        var index :Int = 0
        sibal = findViewById(R.id.button)
        clear = findViewById(R.id.clear_btn)
        year = findViewById(R.id.year)
        num1 = findViewById(R.id.lotto1)
        num2 = findViewById(R.id.lotto2)
        num3 = findViewById(R.id.lotto3)
        num4 = findViewById(R.id.lotto4)
        num5 = findViewById(R.id.lotto5)
        num6 = findViewById(R.id.lotto6)
        val random = Random()
        val lotto = ArrayList<Int>()

        clear.setOnClickListener {
            lotto.clear()
            index = 0
            num1.text = ""
            num2.text = ""
            num3.text = ""
            num4.text = ""
            num5.text = ""
            num6.text = ""
            year.text = "다시"
        }

        sibal.setOnClickListener {
            var num = random.nextInt(45)
            if (lotto.contains(num)){
                num = random.nextInt(45)
                lotto.add(num)
            }else if(num == 0){
                num = random.nextInt(45)
                lotto.add(num)
            }else{
                lotto.add(num)
            }
            if(index > 5){
                num = 0
            }
            when (index) {
                0 -> {
                    Log.d("hello", "d2")
                    num1.text = "${lotto[index]}"
                }
                1 -> {
                    num2.text = "${lotto[index]}"
                }
                2 -> {
                    num3.text = "${lotto[index]}"
                }
                3 -> {
                    num4.text = "${lotto[index]}"
                }
                4 -> {
                    num5.text = "${lotto[index]}"
                }
                5 -> {
                    num6.text = "${lotto[index]}"
                }
            }
            index += 1
            year.text = "${num}번"
        }



    }
}