package com.example.kotlin_android_programming_study
//Activity는 안드로이드 SDK클래스인 Activity의 인스턴스(객체)이다.
//Activity의 서브 클래스를 만들어서 앱의 기능을 구현한다.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class GeoQuizApp : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_quiz_app)


    trueButton = findViewById(R.id.true_button)
    falseButton = findViewById(R.id.false_button)


        trueButton.setOnClickListener{

            Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_SHORT).show()


        }
        falseButton.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show()

        }



    }
}