package com.example.kotlin_android_programming_study.Chaptermemo

/*
/* Chapter4*/
UI 상태 유지하기

문제 상황 : 장치가 회전할 때 액티비티가 소멸되고 재생성되면서 기존 데이터가 보존되지 않고 초기화되는 결함이 발생한다.
이런 결함을 해결하기 위해 MainActivity 인스턴스가 currentIndex 직전 값을 알아야한다.

1. ViewModel 클래스 프로젝트에 추가하기(dependencies에 lifecycle-extension 라이브러리 추가)

viewModel은 특정 액티비티 화면과 연동되며, 해당 화면에 보여줄 데이터를 형식화하는 로직을 두기 좋은 곳이다.
viewModel은 모델 객체와 연동되어 모델을 장식한다.
ViewModel의 생명주기는 장치의 구성 변경이 생겨도 계속 존재하다가 액티비티가 종료될 때만 소멸되기 때문에 장치가 회전되어도 UI 상태 데이터를 메모리에 보존할 수 있다.
**Activity와 ViewModel은 단방향 관계이다. 즉 Activity는 ViewModel을 참조하지만, ViewModel은 Activity를 참조하지 않는다. ViewModel은 Activity나 다른 뷰를 참조할 경우 메모리 유실이 생길 수 있다.


                    MainActivity.onDestroy() 호출
                        Activity.isFinishing?
                false                          true
   QuizViewModel이 메모리에 남아있음        QuizViewModel이 소멸됨


                          현재 앱에서 구동방식

              회전 이전          회전          회전 이후
           MainActivity                    MainActivity
           QuizViewModel   QuizViewModel   QuizViewModel
 */