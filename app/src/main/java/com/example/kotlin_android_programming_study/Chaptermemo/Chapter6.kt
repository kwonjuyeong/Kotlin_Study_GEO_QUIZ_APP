package com.example.kotlin_android_programming_study.Chaptermemo

/*
Chapter6
두 번째 액티비티 만들기

액티비티에서 다른 액티비티로 이동하기 위해서 -> startActivity(Intent)함수를 사용한다.
액티비티에서 startActivity를 호출하면 이 호출은 안드로이드 운영체제로 전달된다.
start(Intent) 호출은 ActivityManager라고 하는 안드로이드 운영체제의 컴포넌트로 전달되고, ActivityManager는 해당 액티비티의 인스턴스를 생성 후 인스턴스의 onCreate함수를 호출한다.

인텐트(intent)란
- 컴포넌트가 운영체제와 통신하는데 사용할 수 있는 객체다.
- 컴포넌트에는 서비스(service), 브로드캐스트 수신자, 콘텐츠 제공자, 액티비티 등이 있다.

* 사용방법
val intent = Intent(context, Activity::class.java)
startActivity(intent)

이와 같이 Context 객체와 Class 객체를 사용해서 생성하는 Intent는 명시적(Explicit) 인텐트이다.
명시적 인텐트는 앱 내부에 있는 액티비티를 시작시키기 위해 사용한다.

Intent Extra
- 인텐트로 화면을 이동할 때 데이터 값을 전달해줘야하는 상황에서는 Intent Extra를 이용한다.
- 엑스트라는 호출하는 액티비티가 인텐트에 포함시킬 수 있는 임의의 데이터로, 생성자 인자로 생각하면 된다.
- 요청된 인텐트 -> 안드로이드 운영체제 -> 수신 액티비티에 전달 -> 해당 인텐트의 엑스트라 데이터를 추출해 사용한다.
- 엑스트라는 키와 값이 한 쌍으로 된 구조이다
- 인텐트를 요청하는 페이지에서는 엑스트라로 무엇을 받는지 알 필요가 없다. 따라서 인텐트를 요청하는 코드를 별도의 함수로 캡슐화 하는 것이 좋다.

startActivityForResult(Intent, Int)
- 자식 액티비티로부터 데이터를 돌려받고 싶을 때 사용하는 함수
- 첫 번째 매개변수는 Intent 객체이고, 두 번째 매개변수는 요청코드(request code)로 사용자가 정의한 정수이다.
- 요청코드는 자식 액티비티에 전달되었다가 부모 액티비티가 다시 돌려받으며, 부모 액티비티가 여러 타입의 자식 액티비티를 시작할 때 어떤 자식 액티비티가
결과를 돌려주는 것인지 알고자 할 때도 사용된다.
+ 자식 액티비티에서 부모 액티비티에 데이터를 돌려주기 위해서는 setResult(resultCode : Int, data : Intent)를 사용한다.


Tip.
1. android:text=""
2. tools:text="" 차이점
1번은 미리보기와 앱 실행 시 둘 다 나오지만
2번은 미리보기에는 표시되지만 앱 실행 시 텍스트가 표시되지 않는다.
 */