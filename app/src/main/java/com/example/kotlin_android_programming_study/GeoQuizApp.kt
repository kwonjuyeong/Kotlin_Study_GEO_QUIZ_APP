package com.example.kotlin_android_programming_study
//Activity는 안드로이드 SDK클래스인 Activity의 인스턴스(객체)이다.
//Activity의 서브 클래스를 만들어서 앱의 기능을 구현한다.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GeoQuizApp : AppCompatActivity() {

    //추가 알고있던 것 메모 : lateinit은 늦은 초기화로 말 그대로 선언만 하고 초기화를 나중에 해줄 수 있다.
    //또한 var로 선언했을 경우에만 사용이 가능하다.
    //null 허용 프로퍼티나 기초 타입 프로퍼티에는 사용할 수 없다.
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_africa, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_korea, true),
        Question(R.string.question_ocean, true),
        Question(R.string.question_mideast, true),
        Question(R.string.question_americas, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_quiz_app)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.privious_button)
        questionTextView = findViewById(R.id.question_textview)


        trueButton.setOnClickListener{
            checkAnswer(true)
            //Toast.makeText(this, R.string.correct_toast,Toast.LENGTH_SHORT).show()
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            //Toast.makeText(this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show()
        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex+1)%questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //questionTextView.setText(questionTextResId)
            updateQuestion()
        }
        previousButton.setOnClickListener {
            currentIndex = (currentIndex+-1)%questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //questionTextView.setText(questionTextResId)
            updateQuestion()
        }
        updateQuestion()

    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer : Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messegeResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messegeResId, Toast.LENGTH_SHORT).show()
    }
}