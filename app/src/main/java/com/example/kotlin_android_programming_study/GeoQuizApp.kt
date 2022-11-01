package com.example.kotlin_android_programming_study
//Activity는 안드로이드 SDK클래스인 Activity의 인스턴스(객체)이다.
//Activity의 서브 클래스를 만들어서 앱의 기능을 구현한다.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "index"
private const val TAG = "Activity"

class GeoQuizApp : AppCompatActivity() {

    //추가 알고있던 것 메모 : lateinit은 늦은 초기화로 말 그대로 선언만 하고 초기화를 나중에 해줄 수 있다.
    //또한 var로 선언했을 경우에만 사용이 가능하다.
    //null 허용 프로퍼티나 기초 타입 프로퍼티에는 사용할 수 없다.
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel : QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    private var correct = 0
    private var array = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_geo_quiz_app)


        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        /*val provider : ViewModelProvider = ViewModelProvider(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel : $quizViewModel")*/


        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_textview)


        trueButton.setOnClickListener{
            checkAnswer(true)
            //array.add(currentIndex)
           // correct += 1
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            //array.add(currentIndex)
        }
        nextButton.setOnClickListener {
            //currentIndex = (currentIndex+1)%questionBank.size
            updateQuestion()
            //Chapter3 챌린지 2번

            /*if(currentIndex + 1  == questionBank.size){
                val hundread : Float = (correct.toFloat() / questionBank.size) * 100
                Toast.makeText(this@GeoQuizApp, "백분율 : $hundread%", Toast.LENGTH_SHORT).show()
                nextButton.isEnabled = false
            //  array.clear()
            //  correct = 0
            }*/
        }
        previousButton.setOnClickListener {
            //currentIndex = (currentIndex-1)%questionBank.size
            updateQuestion()
        }
    }

    private fun updateQuestion(){
        /*
        if(array.contains(currentIndex)){
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        }else{
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }*/

        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer : Boolean){
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messegeResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messegeResId, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onRestart() {
        super.onRestart()

    }
}