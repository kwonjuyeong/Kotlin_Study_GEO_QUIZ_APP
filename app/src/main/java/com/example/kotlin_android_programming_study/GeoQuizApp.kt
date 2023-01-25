package com.example.kotlin_android_programming_study
//Activity는 안드로이드 SDK클래스인 Activity의 인스턴스(객체)이다.
//Activity의 서브 클래스를 만들어서 앱의 기능을 구현한다.

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val KEY_INDEX = "index"
private const val TAG = "GeoQuizApp"
private const val REQUEST_CODE_CHEAT = 0

class GeoQuizApp : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var cheatButton : Button
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
        cheatButton = findViewById(R.id.cheat_button)
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
        cheatButton.setOnClickListener{
            //val intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@GeoQuizApp, answerIsTrue)
            //startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
        updateQuestion()

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
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when{
            quizViewModel.isCheater -> R.string.judgement_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != Activity.RESULT_OK){
            return
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }

    }

}