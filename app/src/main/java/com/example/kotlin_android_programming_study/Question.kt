package com.example.kotlin_android_programming_study

import androidx.annotation.StringRes
/*
data class인 Question은 2개의 데이터(질문, 정답)을 가진다.
본 공부에서 Model 클래스에 data class를 사용한다.
코틀린에서 클래스 이름 다음에 지정되는 괄호는 기본 생성자와 속성을 나타낸다.

Model : Question class
Controller : Activity
View : TextView, Button(3)
*/
data class Question(@StringRes val textResId : Int, val answer : Boolean)

