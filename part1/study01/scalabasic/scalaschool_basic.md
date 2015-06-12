
#싸이그래머[스칼라ML] 파트1-1회차 발표자 이상열
##스칼라를 사용해야 하는 이유는?
 + 풍부한 표현력(1급 함수, 클로져)
 + 간결함 (타입 추론, 함수 리터럴)
 + 자바와의 혼용 가능 (자바 라이브러리 재사용 가능, 자바 도구를 재사용 가능, 성능의 손실 없이 사용 가능)

## 스칼라를 어떻게 쓸 수 있나?
 + 자바 바이트코드로 컴파일됨
 + 표준적인 자바 VM에서 잘 동작함 (심지어는 달빅(Dalvik)과 같은 비표준 JVM에서도 동작, 자바 컴파일러를 만든 사람이 스칼라 컴파일러도 만듦

##식 
 + 식을 입력하면 인터프리터가 결과를 출력한다. 이때, res0은 결과 값에 인터프리터가 부여한 이름이다. 이 값의 타입은 Int이고, 값은 2라는 정수이다. 스칼라의 (거의) 모든 문장은 식이다.


    1+1


    [36mres1[0m: [32mInt[0m = [32m2[0m


##값
 + 식의 결과에 이름을 붙일 수 있다.


    val two = 1+1 


    [36mtwo[0m: [32mInt[0m = [32m2[0m


##변수
 + 값과 이름의 관계를 변경할 필요가 있다면, var를 사용해야만 한다.


    var name = "steve"


    [36mname[0m: [32mjava.lang.String[0m = [32m"steve"[0m



    name = "marius"


    


##함수
 + def를 사용해 함수를 만들 수 있다.
 + 스칼라에서는 함수 인자의 타입을 명시해야 한다. 인터프리터는 기꺼이 타입 시그니쳐를 다시 표시해줄 것이다.
 + 인자가 없는 함수의 경우 호출시 괄호를 생략할 수도 있다.
 + 이름없는 함수
 + 함수가 여러 식으로 이루어진 경우, {}를 사용해 이를 위한 공간을 만들 수 있다.
 ![그림참고](http://www.slipp.net/wiki/download/attachments/23199906/image2015-4-27%201%3A33%3A10.png?version=1&modificationDate=1430065988000&api=v2)


    def addOne(m: Int): Int = m + 1


    defined [32mfunction [36maddOne[0m



    val three = addOne(2)


    [36mthree[0m: [32mInt[0m = [32m3[0m



    def three() = 1 + 2


    defined [32mfunction [36mthree[0m



    three()


    [36mres8[0m: [32mInt[0m = [32m3[0m



    three


    [36mres9[0m: [32mInt[0m = [32m3[0m



    (x: Int) => x + 1  // Anonymous Functions


    [36mres10[0m: [32mInt => Int[0m = <function1>



    res10(1)


    [36mres11[0m: [32mInt[0m = [32m2[0m



    val addOne = (x: Int) => x + 1


    [36maddOne[0m: [32mInt => Int[0m = <function1>



    addOne(1)


    [36mres149[0m: [32mInt[0m = [32m2[0m



    def timesTwo(i: Int): Int = {
        println("hello world")
        i * 2
    }


    defined [32mfunction [36mtimesTwo[0m



    { i: Int =>
    println("hello world")
    i * 2
    }


    [36mres151[0m: [32mInt => Int[0m = <function1>


## 인자의 일부만 사용해 호출하기(부분 적용, Partial application)
- 함수 호출시 밑줄(_)을 사용해 일부만 적용할 수 있다. 그렇게 하면 새로운 함수를 얻는다. 스칼라에서 밑줄은 문맥에 따라 의미가 다르다. 하지만 보통 이름 없는 마법의 문자로 생각해도 된다. '{ _ + 2 }` 이라는 문맥에서 밑줄은 이름이 없는 매개변수를 가리킨다. 다음과 같이 이를 사용할 수 있다.
- 인자 중에서 원하는 어떤 것이든 부분 적용이 가능하다. 꼭 맨 마지막 위치가 아니라도 아무 곳에서 밑줄을 넣을 수 있다. (역주: 사실은 _는 부분 적용하는 것에서 제외되는 위치가 혼동되지 않도록 표시해주는 역할을 한다. 혼동하면 안되는 것은 _이 부분적용이 아니고, _를 제외하고 호출하는 코드에 적힌 실제 인자가 부분적용의 인자값이라는 것이다. 다만 이 scala school에서는 _를 일관되게 부분 적용이라고 부르고 있지만, 역자 생각에 이는 틀린 것이다. 다만 의미의 혼동을 피하기 위해서 원저자의 표현을 그대로 사용할 것이다.)


    def adder(m: Int, n: Int) = m + n


    defined [32mfunction [36madder[0m



    val add2 = adder(2, _:Int)


    [36madd2[0m: [32mInt => Int[0m = <function1>



    add2(3)


    [36mres154[0m: [32mInt[0m = [32m5[0m


## 커리 함수(Curried functions)
- 떄로 함수의 인자중 일부를 적용하고, 나머지는 나중에 적용하게 남겨두는 것이 더 쓸모있는 경우가 있다.
- 다음은 두 수를 곱하는 곱셈기를 만들 수 있는 함수이다. 첫 호출시 승수를 지정하고, 나중에 피승수를 지정할 수 있다.

- 함수의 첫번째 인자를 넘기면 그 결과로 두번째 인자를 받는 함수가 반환이 되고 반환된 함수에 두번째 인자를 넘기면 기존-커리되지 않은 함수-의 결과가 변환됨.
- A(x,y) -> z인 함수를 C'A(x)(y) -> z로 바꾼다고 생각하면 됨.


    def multiply(m: Int)(n: Int): Int = m * n


    defined [32mfunction [36mmultiply[0m



    multiply(2)(3)


    [36mres156[0m: [32mInt[0m = [32m6[0m



    val timesTwo = multiply(2) _


    [36mtimesTwo[0m: [32mInt => Int[0m = <function1>



    timesTwo(3)


    [36mres158[0m: [32mInt[0m = [32m6[0m



    (adder _).curried


    [36mres159[0m: [32mInt => (Int => Int)[0m = <function1>



    val curriedAdd = (adder _).curried


    [36mcurriedAdd[0m: [32mInt => (Int => Int)[0m = <function1>



    val addTwo = curriedAdd(2)


    [36maddTwo[0m: [32mInt => Int[0m = <function1>



    addTwo(4)


    [36mres162[0m: [32mInt[0m = [32m6[0m



    def justFun(a:Int, b:Int){
        val sum = a + b
        println("result : " + sum)
    }


    defined [32mfunction [36mjustFun[0m



    def curriedFun(a:Int)(b:Int){
        val sum = a + b
        println("result : " + sum)
    }


    defined [32mfunction [36mcurriedFun[0m



    justFun(3,4)

    result : 7



    



    curriedFun(3)(4)

    result : 7



    



    val one = curriedFun(3)_


    [36mone[0m: [32mInt => Unit[0m = <function1>



    one(4)

    result : 7



    


## 가변 길이 인자
- 동일한 타입의 매개변수가 반복되는 경우를 처리할 수 있는 특별한 문법이 있다. 여러 문자열에 동시에 `capitalize`를 호출하고 싶을 경우 다음과 같이 쓸 수 있다.


    def capitalizeAll(args: String*) = {
      args.map { arg =>
        arg.capitalize
      }
    }


    defined [32mfunction [36mcapitalizeAll[0m



    capitalizeAll("rarity", "applejack")


    [36mres28[0m: [32mSeq[String][0m = [33mArrayBuffer[0m([32m"Rarity"[0m, [32m"Applejack"[0m)



    def sum(args: Int*) = {
          var result = 0
          for (arg <- args) result += arg
          result
    }


    defined [32mfunction [36msum[0m



    val s = sum(1,4,9,16,25)


    [36ms[0m: [32mInt[0m = [32m55[0m


## 클래스
- 클래스 안에서 메소드는 def로, 필드는 val로 정의한다. 메소드는 단지 클래스(객체)의 상태를 억세스할 수 있는 함수에 지나지 않는다.
- 생성자 : 스칼라에서는 생성자가 특별한 메소드로 따로 존재하지 않는다. 클래스 몸체에서 메소드 정의 부분 밖에 있는 모든 코드가 생성자 코드가 된다. Calculator 예제를 생성자가 인자를 받아 내부 상태를 초기화하도록 변경해 보자.


    class Calculator {
            val brand: String = "HP"
            def add(m: Int, n: Int): Int = m + n
          }


    defined [32mclass [36mCalculator[0m



    val calc = new Calculator


    [36mcalc[0m: [32mcmd32.INSTANCE.$ref$cmd31.Calculator[0m = cmd31$$user$Calculator@20d7eefc



    calc.add(1, 2)


    [36mres33[0m: [32mInt[0m = [32m3[0m



    calc.brand


    [36mres34[0m: [32mString[0m = [32m"HP"[0m



    class Calculator(brand: String) {
      val color: String = if (brand == "TI") {
        "blue"
      } else if (brand == "HP") {
        "black"
      } else {
        "white"
      }
    
      def add(m: Int, n: Int): Int = m + n
    }
    


    defined [32mclass [36mCalculator[0m



    val calc = new Calculator("HP")


    [36mcalc[0m: [32mcmd42.INSTANCE.$ref$cmd35.Calculator[0m = cmd35$$user$Calculator@35394075



    calc.color


    [36mres41[0m: [32mString[0m = [32m"white"[0m


## 곁다리: 함수 대 메소드
- 함수와 메소드는 서로 바꿔쓸 수 있는 개념이다. 함수와 메소드는 매우 유사하며, 실제 여러분이 호출 중인 어떤 것이 함수인지 메소드인지를 기억하지 못할 수도 있다. 실제 메소드와 함수의 차이와 마주치게 되면 혼동이 올 수도 있다.


    class C {
            var acc = 0
            def minc = { acc += 1 }
            val finc = { () => acc += 1 }
          }


    defined [32mclass [36mC[0m



    val c = new C


    [36mc[0m: [32mcmd44.INSTANCE.$ref$cmd43.C[0m = cmd43$$user$C@40a869b



    c.minc


    



    c.finc


    [36mres46[0m: [32m() => Unit[0m = <function0>



    val fMsg = () => "Hello" 


    [36mfMsg[0m: [32m() => java.lang.String[0m = <function0>



    def mMsg() = "Hello" 


    defined [32mfunction [36mmMsg[0m



    println(mMsg)

    Hello



    



    println(fMsg)

    <function0>



    



    println(fMsg())

    Hello



    


## 상속
- See Also “효율적인 스칼라(effective scala)”에서는 하위클래스가 상위클래스와 실제 다르지 않을 경우 타입 별명이 extends(확장)보다 더 낫다고 말한다. 스칼라 여행(Tour of Scala)에서는 상속하기(Subclassing)에 대해 다루고 있다.
- 매소드 중복정의(Overloading) ; 함수를 상속받아 재정의(추상메소드는 그냥 진행, 그렇지 않는 경우 메소드 앞에 override를 붙임)
- 추상클래스 : 추상 클래스(abstract class)는 메소드 정의는 있지만 구현은 없는 클래스이다. 대신 이를 상속한 하위클래스에서 메소드를 구현하게 된다. 추상 클래스의 인스턴스를 만들 수는 없다.


    class ScientificCalculator(brand: String) extends Calculator(brand) {
      def log(m: Double, base: Double) = math.log(m) / math.log(base)
    }


    defined [32mclass [36mScientificCalculator[0m



    class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
      def log(m: Int): Double = log(m, math.exp(1))
    }


    defined [32mclass [36mEvenMoreScientificCalculator[0m



    abstract class Shape {
            def getArea():Int    
    }


    defined [32mclass [36mShape[0m



    class Circle(r: Int) extends Shape {
            def getArea():Int = { r * r * 3 }
          }


    defined [32mclass [36mCircle[0m



    val s = new Shape


    Compilation Failed

    Main.scala:44: class Shape is abstract; cannot be instantiated

      new Shape 

      ^



    val c = new Circle(2)


    [36mc[0m: [32mcmd180.INSTANCE.$ref$cmd179.Circle[0m = cmd179$$user$Circle@abd44d8


## 트레잇(Traits, 특성이라는 뜻)
- 트레잇(trait)은 다른 클래스가 확장(즉, 상속)하거나 섞어 넣을 수 있는(이를 믹스인Mix in 이라 한다) 필드와 동작의 모음이다.
- 추상클래스 대신 트레잇를 사용해야 하는 경우는 언제인가? 인터페이스 역할을 하는 타입을 설계할 때 트레잇과 추상클래스 중 어떤 것을 골라야할까? 두 가지 다 어떤 동작을 하는 타입을 만들 수 있으며, 확장하는 쪽에서 일부를 구현하도록 요청한다. 중요한 규칙은 다음과 같다.
 - 트레잇을 사용하는 것이 낫다. 클래스는 오직 하나만 상속(extend)할 수 있지만, 트레잇은 여러 가지를 받아 사용할 수 있다.
 - 생성자 매개변수가 필요한 경우라면 추상 클래스를 사용하라. 추상 클래스의 생성자는 매개변수를 받을 수 있지만, 트레잇의 생성자는 그렇지 않다. 예를 들어 trait t(i: Int) {} 에서 i 매개변수는 허용되지 않는다.
 - 당신이 이런 질문을 한 첫번째 사람은 아니다. 스택 오버플로우에서 트레잇과 추상 클래스의 비교, 추상 클래스와 트레잇의 차이, 또는 스칼라 프로그래밍: 트레잇냐 아니냐 그것이 문제로다? 등을 참조하라.



    trait Car {
      val brand: String
    }
    
    trait Shiny {
      val shineRefraction: Int
    }


    defined [32mtrait [36mCar[0m
    defined [32mtrait [36mShiny[0m



    class BMW extends Car {
      val brand = "BMW"
    }
    


    defined [32mclass [36mBMW[0m



    class BMW extends Car with Shiny {
      val brand = "BMW"
      val shineRefraction = 12
    }


    defined [32mclass [36mBMW[0m


##타입
- 앞에서 숫자 타입중 하나인 Int를 인자로 받는 함수를 보았다. 모든 타입의 값을 처리할 수 있는 일반적(generic)인 함수를 만들 수도 있다. 일반적 함수를 만들 때는 각괄호([])안에 타입 매개변수를 추가한다. 아래는 키와 값을 가지는 일반적인 캐시를 보여준다.

- 제네릭함이란 코드를 타입에 대하여 파라미터화 할 수 있는 능력이다. 이해를 돕기 위해 하나의 예를 들어 보자. 연결 리스트 라이브러리를 작성하는 프로그래머는 리스트의 원소 타입을 도대체 무엇으로 해야 할지 고민에 빠지게 된다. 이 연결 리스트는 서로 다른 많은 상황에서 사용 될 수 있기 때문에 원소의 타입이 반드시 Int 또는 반드시 Double이 될 것이라 미리 결정하는 것은 불가능하다. 이렇게 결정해 두는 일은 완전히 임의적이며 라이브러리의 사용에 있어 필요 이상의 심한 제약으로 작용 한다.


    trait Cache[K, V] {
      def get(key: K): V
      def put(key: K, value: V)
      def delete(key: K)
    }


    defined [32mtrait [36mCache[0m



    def remove[K](key: K)


    <console>:1: '=' expected but eof found.

    def remove[K](key: K)

                         ^


##apply 메소드
- apply 메소드를 사용하면 클래스나 객체의 용도가 주로 하나만 있는 경우를 아주 멋지게 표현할 수 있다.
- apply를 정의하면 메소드를 호출하듯 객체 인스턴스를 호출할 수 있다. 객체 인스턴스를 호출하면 그 객체(클래스)에 정의된 apply()가 호출된다. 자세한 것은 나중에 살펴볼 것이다.
- apply는 말 그대로 어떤 함수를 적용시킨다는 의미, apply와 함께 들어오는 인자에 대해서 어떤 함수를 적용 시킨다는 것.


    class Foo {}


    defined [32mclass [36mFoo[0m



    object FooMaker {
            def apply() = new Foo
          }


    defined [32mobject [36mFooMaker[0m



    val newFoo = FooMaker()


    [36mnewFoo[0m: [32mcmd186.INSTANCE.$ref$cmd185.Foo[0m = cmd185$$user$Foo@53597f23



    class Bar {
            def apply() = 0
          }


    defined [32mclass [36mBar[0m



    val bar = new Bar


    [36mbar[0m: [32mcmd189.INSTANCE.$ref$cmd188.Bar[0m = cmd188$$user$Bar@222709c2



    bar()


    [36mres190[0m: [32mInt[0m = [32m0[0m



    val f1 = (x:Int, y:Int) => x + y


    [36mf1[0m: [32m(Int, Int) => Int[0m = <function2>



    f1.apply(2,3)


    [36mres67[0m: [32mInt[0m = [32m5[0m



    f1(2,3)


    [36mres68[0m: [32mInt[0m = [32m5[0m


## 객체
- 객체(여기서는 object 키워드로 선언하는 객체를 말함)는 클래스의 유일한 인스턴스를 넣기 위해 사용한다. 보통 팩토리에 사용된다.
- 클래스와 객체가 같은 이름을 가질 수도 있다. 이런 객체는 ‘짝 객체(Companion Object)’라 한다. 보통 팩토리를 만들 때 짝 객체를 사용한다.


    object Timer {
      var count = 0
    
      def currentCount(): Long = {
        count += 1
        count
      }
    }


    defined [32mobject [36mTimer[0m



    Timer.currentCount()


    [36mres192[0m: [32mLong[0m = [32m1[0mL



    class Bar(foo: String)
    
    object Bar {
      def apply(foo: String) = new Bar(foo)
    }


    defined [32mclass [36mBar[0m
    defined [32mobject [36mBar[0m


## 함수는 객체이다
- 스칼라에 대해 이야기할 떄, 객체-함수형 프로그래밍이라는 말을 하고는 한다. 그 말이 무슨 뜻일까? 함수란 실제로 무엇일까?
- 함수는 트레잇의 집합이다. 구체적으로 말하자면, 인자를 하나만 받는 함수는 Function1 트레잇의 인스턴스이다. 이 트레잇에는 앞에서 설명했던 apply()가 정의되어 있다. 따라서 함수를 호출하듯 객체를 호출할 수 있다.
- 스칼라에는 Function이 1부터 22까지 준비되어 있다. 22인 이유는? 그냥 그렇게 정한 것이다. 저자는 인자가 22개 보다 더 많이 필요한 함수를 본 적이 없다. 22개면 충분하리라 본다.
- apply를 통한 편리 문법(syntactic sugar)을 통해 객체와 함수 프로그래밍 양쪽을 잘 통합할 수 있다. 여러분은 클래스를 여기저기 넘기면서 함수 처럼 호출해 사용할 수 있고, 함수는 한꺼풀 벗겨보면 단지 클래스의 인스턴스일 뿐이다.
- 그렇다면 클래스의 메소드를 정의할 때마다 실제로 Function*의 인스턴스가 만들어지는 걸까? 아니다. 클래스 내부의 메소드는 메소드이다. repl(read eval print loop. 입력을 받아 값을 계산하고 결과를 출력하는 루프. 스칼라 인터프리터라 생각하면 대략 맞다)에서 정의한 개별 메소드는 Function*의 인스턴스이다.
- Function*을 확장한 클래스를 정의할 수도 있다. 물론 이런 클래스도 ()로 호출할 수 있다.


    object addOne extends Function1[Int, Int] {
           def apply(m: Int): Int = m + 1
          }


    defined [32mobject [36maddOne[0m



    addOne(1)


    [36mres195[0m: [32mInt[0m = [32m2[0m



    class AddOne extends Function1[Int, Int] {
            def apply(m: Int): Int = m + 1
          }


    defined [32mclass [36mAddOne[0m



    val plusOne = new AddOne()


    [36mplusOne[0m: [32mcmd197.INSTANCE.$ref$cmd196.AddOne[0m = <function1>



    plusOne(1)


    [36mres198[0m: [32mInt[0m = [32m2[0m



    class AddOne extends (Int => Int) {
      def apply(m: Int): Int = m + 1
    }


    defined [32mclass [36mAddOne[0m


## 패키지
- 코드를 패키지로 구성할 수 있다.
- 위와 같이 파일의 맨 앞에서 선언하면 파일 내의 모든 것이 위 패키지 안에 포함된다.
- 값이나 함수는 클래스나 객체 바깥에 존재할 수 없다. 객체(여기서도 object로 선언한 객체를 의미함)를 사용하면 정적인(자바의 정적 함수와 동일) 함수를 관리하기 쉽다.



    package com.twitter.example
    
    object colorHolder {
      val BLUE = "Blue"
      val RED = "Red"
    }


    <console>:1: '{' expected but eof found.

    package com.twitter.sample

                              ^



    println("the color is: " + com.twitter.example.colorHolder.BLUE)


    Compilation Failed

    Main.scala:42: object twitter is not a member of package com

    println("the color is: " + com.twitter.example.colorHolder.BLUE)

                                   ^



    object colorHolder {
            val Blue = "Blue"
            val Red = "Red"
          }


    defined [32mobject [36mcolorHolder[0m


##패턴 매칭
- 패턴 매치는 스칼라에서 가장 유용한 기능 중 하나이다. 값에 대해 매칭할 수 있다.
- 기본적인 컨셉트는 자바의 switch 와 비슷하다. 하지만 자바의 switch 는 constant 값에만 적용할수 있는 반면에 스칼라의 페턴매칭은 constant 값 이외에도 case class, variable, collection 등등 여러가지 유용한 패턴을 사용할수 있다.


    val times = 1
    
    times match {
      case 1 => "one"
      case 2 => "two"
      case _ => "some other number"
    }


    [36mtimes[0m: [32mInt[0m = [32m1[0m
    [36mres73_1[0m: [32mjava.lang.String[0m = [32m"one"[0m



    times match {
      case i if i == 1 => "one"
      case i if i == 2 => "two"
      case _ => "some other number"
    }


    [36mres74[0m: [32mjava.lang.String[0m = [32m"one"[0m


##타입에 대해 매치시키기
- match를 사용해 타입이 다른 값을 서로 다른 방식으로 처리 가능하다.


    def bigger(o: Any): Any = {
      o match {
        case i: Int if i < 0 => i - 1
        case i: Int => i + 1
        case d: Double if d < 0.0 => d - 0.1
        case d: Double => d + 0.1
        case text: String => text + "s"
      }
    }


    defined [32mfunction [36mbigger[0m


##클래스 멤버에 대해 매치시키기
- 앞에서 봤던 계산기 예제를 다시 떠올려보자. 타입(계산기의 유형)에 따라 계산기를 구분하자.


    def calcType(calc: Calculator) = calc match {
      case c if c.brand == "hp" && calc.model == "20B" => "financial"
      case c if c.brand == "hp" && calc.model == "48G" => "scientific"
      case c if c.brand == "hp" && calc.model == "30B" => "business"
      case _ => "unknown"
    }


    defined [32mfunction [36mcalcType[0m


##케이스 클래스(case class)
- 케이스 클래스는 손쉽게 내용을 어떤 클래스에 저장하고, 그에 따라 매치를 하고 싶은 경우 사용한다. new를 사용하지 않고도 케이스 클래스의 인스턴스 생성이 가능하다.
- 케이스 클래스는 자동으로 생성자 인자에 따른 동등성 검사를 제공하며, 또한 보기 좋은 toString 메소드도 제공한다.
- 케이스 클래스 안에도 일반 클래스와 똑같이 메소드를 정의할 수 있다.
- 케이스 클래스와 패턴 매칭 : 케이스 클래스는 패턴 매치와 사용하기 위해 설계된 것이다. 앞의 계산기 분류 예제를 간략하게 만들어보자.



    case class Calculator(brand: String, model: String)


    defined [32mclass [36mCalculator[0m



    val hp20b = Calculator("hp", "20b")


    [36mhp20b[0m: [32mcmd106.INSTANCE.$ref$cmd105.Calculator[0m = [33mCalculator[0m([32m"hp"[0m, [32m"20b"[0m)



    val hp20b = Calculator("hp", "20b")


    [36mhp20b[0m: [32mcmd107.INSTANCE.$ref$cmd105.Calculator[0m = [33mCalculator[0m([32m"hp"[0m, [32m"20b"[0m)



    val hp20B = Calculator("hp", "20b")


    [36mhp20B[0m: [32mcmd108.INSTANCE.$ref$cmd105.Calculator[0m = [33mCalculator[0m([32m"hp"[0m, [32m"20b"[0m)



    hp20b == hp20B


    [36mres109[0m: [32mBoolean[0m = true



    val hp20b = Calculator("hp", "20B")
    val hp30b = Calculator("hp", "30B")
    
    def calcType(calc: Calculator) = calc match {
      case Calculator("hp", "20B") => "financial"
      case Calculator("hp", "48G") => "scientific"
      case Calculator("hp", "30B") => "business"
      case Calculator(ourBrand, ourModel) => "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
      //case Calculator(_, _) => "Calculator of unknown type"
      //case _ => "Calculator of unknown type"
      //case c@Calculator(_, _) => "Calculator: %s of unknown type".format(c)
    }


    [36mhp20b[0m: [32mcmd110.INSTANCE.$ref$cmd105.Calculator[0m = [33mCalculator[0m([32m"hp"[0m, [32m"20B"[0m)
    [36mhp30b[0m: [32mcmd110.INSTANCE.$ref$cmd105.Calculator[0m = [33mCalculator[0m([32m"hp"[0m, [32m"30B"[0m)
    defined [32mfunction [36mcalcType[0m



    calcType(Calculator("hp","20B"))


    [36mres111[0m: [32mjava.lang.String[0m = [32m"financial"[0m



    calcType(Calculator("hp","48G"))


    [36mres112[0m: [32mjava.lang.String[0m = [32m"scientific"[0m



    calcType(Calculator("kth","h3"))


    [36mres113[0m: [32mjava.lang.String[0m = [32m"Calculator: kth h3 is of unknown type"[0m


##예외
- 스칼라에서는 예외 처리시 try-catch-finally 문법에 패턴 매치를 사용할 수 있다.


    try {
      remoteCalculatorService.add(1, 2)
    } catch {
      case e: ServerIsDownException => log.error(e, "the remote calculator service is unavailble. should have kept your trustry HP.")
    } finally {
      remoteCalculatorService.close()
    }


    Compilation Failed

    Main.scala:43: not found: value remoteCalculatorService

      remoteCalculatorService.add(1, 2)

      ^

    Main.scala:45: not found: type ServerIsDownException

      case e: ServerIsDownException => log.error(e, "the remote calculator service is unavailble. should have kept your trustry HP.")

              ^

    Main.scala:45: not found: value log

      case e: ServerIsDownException => log.error(e, "the remote calculator service is unavailble. should have kept your trustry HP.")

                                       ^

    Main.scala:47: not found: value remoteCalculatorService

      remoteCalculatorService.close()

      ^



    val result: Int = try {
      remoteCalculatorService.add(1, 2)
    } catch {
      case e: ServerIsDownException => {
        log.error(e, "the remote calculator service is unavailble. should have kept your trustry HP.")
        0
      }
    } finally {
      remoteCalculatorService.close()
    }


    Compilation Failed

    Main.scala:43: not found: value remoteCalculatorService

      remoteCalculatorService.add(1, 2)

      ^

    Main.scala:45: not found: type ServerIsDownException

      case e: ServerIsDownException => {

              ^

    Main.scala:46: not found: value log

        log.error(e, "the remote calculator service is unavailble. should have kept your trustry HP.")

        ^

    Main.scala:50: not found: value remoteCalculatorService

      remoteCalculatorService.close()

      ^

