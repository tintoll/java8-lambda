# Java8 에 추가된 기능



### Interface에 default 메서드, static 메서드 사용이 가능해진 것이 대표적인 변화

#### default Method

- java 8 이전까지 Interface의 abstract 메서드는 반드시 클래스에서 구현해야하고, Interface에 새로운 메서드가 추가되면, 구현 클래스를 반드시 수정해야 하므로 **바이너리 호환성에 이슈**가 생길 수가 있었음.
- java 8 이후에는 Interface에 새롭게 확장할 메서드를 default 메서드로 지정할 경우에도 기존 클래스에도 영향이 없음.
- 즉, default 메서드로 구현하는 모든 클래스들이 동일한 기능을 사용할 수 있도록 하는 동시에, Interface에 변경이 생기더라도 **바이너리 호환성을 유지** 할수 있는 방법을 제공함.

#### static method

- 인터페이스에서 static method 사용 가능해짐.
- Factory 메소드를 인터페이스의 static method로 구현
- 예전에는 static메소드를 포함한 Companion class를 별도로 두었음
  - Collection / Collections
  - Path / Paths



### Lambda expression을 도입

```java
// 기존 문법 사용시 
new Thread(new Runnable(){
    public void run() {
        System.out.println("Thread run!!");
    }
}).run();

// lambda expression 사용시 
new Thread(() -> System.out.println("Thread run!!"));

```

#### Functional Interface

- Lambda는 **하나의 abstract 메서드만을 가진 인터페이스를 Functional Interface** 라고 부른다.

- Lambda expression을 지원하는 java.util.function 패키지가 추가되었다.

  

### Stream API 추가

#### Java8은 Stream과 Lambda를 활용하여 이전보다 훨씬 세련되고 향상된 방법으로 Collection을 처리한다.

- Stream은 순차(sequential), 병렬(parallel) 두 종류가 존재한다.
- 순차 Stream은 싱글 스레드로 순차적으로 처리되며, 병렬 Stream은 Fork/Join 프레임워크로 구현된 메서드에 의해 병렬로 처리된다.

```java
// 순차
int totalCountOfOrder = orderList.stream()
     						.filter(b -> b.getProduct() == "iPhone")
    						.map(b -> b.getAmount())
    						.sum();
// 병렬
OptionalDouble averageAge = pl.parallelStream()
    						.filter(search.getCriteria("allPilots"))
    						.mapToDouble(p -> p.getAge())
    						.average();
```

#### Stream 장단점

- 장점은 Laziness(*최종연산 메서드에서 실행이된다.*) 이다. 즉 Collection의 interation 처리를 자바에게 맡겨 둠으로써 JVM이 최적화할 수 있는 기회를 제공한다.
- 단점은 재사용이 불가능하다. 한번 사용된 Stream은 재사용이 불가능하며, 필요에 따라 새롭게 만들어야 한다.



### Time 및 Date 관련 API 추가

#### 기존에 알려진 문제점

- 년은 1900년 부터 시작한다. 1900년도 이전에 대한 처리는 workaround가 필요
- 월이 1부터가 아닌 0부터 시작한다. 그러므로 12월은 실제로 숫자 11이다.
- mutable하므로 thread safe하지 않다.
- performance 문제

#### 새로운 Time And Date API

- java.time 패키지의 LocalDate, LocalTime, LocalDateTime, ZonedDateTime 클래스
- LacalDate는 년-월-일, LocalTime는 시-분-초-나노초, LocalDateTime은 년-월-일-시-분-초를 내부적으로 저장함.



### Type Annotation / Repeating Annotation

- Type Annotaion은 새롭게 Type 시스템에 추가된 기능으로 새로운 operator나 implements 구문, throws구문에 추가할수 있는 annotaion이다.

  ```java
  @NotNull String str1 = ...;
  @Email String str2 = ...;
  @NotNull @NotBlank String str3 = ...;
  Map.@NonNull Entry = ...;
  myString = (@NonNull String)myObject;
  void monitorTemperature() throws @Critical TemperatureException {...}
  void authenticate() throws @Fatal @Logged AccessDeniedException {...}
  ```

- Repeating Annotation은 기존에는 중복 선언이 허용되지 않았지만 java8부터는 동일한 Annotation의 중복 선언이 허용된다. Java.lang.annotation.Repeatable이 새로 소개되었다.

  ```java
  @Schedule(dayOfMonth="last")
  @Schedule(dayOfWeek="Fn", hout="23")
  public void dePeriodicCleanup(){....}
  ```



# 함수형 프로그래밍



### 함수형 프로그래밍이란?

- 계산을 수학적 함수의 평가로 취급하고 상태와 가변 데이터를 멀리하는 프로그래밍 패러다임이다. 
- 함수형 프로그래밍은 프로그램을 오직 순수함수(pure function)들로 만 작성되어 진다.
- 즉, Side Effect(부수효과)가 없는 함수들로만 구축한다는 의미이다.
- 부수효과를 제거할 경우에 프로그램의 동작을 이해하고 예측하는 것이 훨씬 쉬워진다.



#### 부수효과(Side Effect)란?

- 변수를 수정하거나, 객체의 필드를 설정한다.
- 예외(Exception)을 던지거나 오류를 내면서 실행을 중단한다.
- 콘솔에 출력하거나 사용자의 입력을 읽어 들인다.
- 파일에 기록하거나 파일에서 읽어 들인다.



### 함수형 프로그래밍의 기본원리들

-----

#### 변경 불가능한 값을 이용

- 함수의 계산을 수행하는 동안 변수에 할당된 값들이 절대로 변하지 않는다.
- 변수에 새로운 값을 설정할 수 있을 뿐이며 일단 값이 설정되면 그 값을 바꿀 수 없다.

#### 함수가 1등 시민

- 함수가 1등 시민(First-class Citizen)이라는 말은 함수를 변수나 자료 구조안에 담을 수 있고, 함수를 인자로 전달할 수 있으며, 반환값으로 사용할 수 있다는 의미이다. (자바에서는 반환은 할수 없다.)

#### 람다와 클로저

- 람다는 익명함수를 말하며, 인수의 리스트와 함수의 본문만을 가지는 함수이다.
- 클로저란 자신이 생성될 때의 scope에서 알 수 있었던 변수를 기억하는 함수이다.

#### 고계함수(Higher-order Function)

- 고계함수는 다른 함수를 인수로 받아 들이거나 함수를 리턴하는 함수를 가리킨다.
- 함수가 정수와 동등한 값으로 다뤄지기 때문에 함수를 인자로 넘기거나 결과 값을 함수로 반환 받을 수 있다. 



### 함수형 프로그래밍의 컨셉

-------

1. 변경 가능한 상태를 불변상태(Immutable)로 만들어 SideEffect를 없애자
   1. 함수 안에서 상태를 관리 하고 상태에 따라서 결과 값이 달라지면 안 되다는 뜻임.
   2. 상태를 사용하지 않음으로 SideEffect를 차단할 수 있다
2. 모든 것은 객체이다.
   1. 클래스 외에 함수 또는 객체이기 때문에 함수를 값으로 할당할 수 있고, 파라미터로 전달 및 결과 값으로 반환이 가능하다. 이 3가지 조건을 만족하는 객체를 1급객체(First-class citizen)라고 한다.
3. 코드를 간결하게, 가독성을 높여 로직에 집중시키자.
   1. Lambda 및 Stream과 같은 API를 통해 보일러 플레이트를 제거하고, 내부에 직접적인 함수 호출을 통해 가독성을 높일 수 있다. 
4. 동시성 작업을 보다 쉽고 안전하게 구현하자.



### Java8에서 함수형 프로그래밍을 어떻게 지원하는가?

-----

1. Functional Interface

   - 함수형 인터페이스란 하나의 abstract 메서드를 가지는 인터페이스이다. 함수형 인터페이시 지정을 위하여 @FunctionalInterface 어노테이션이 도입되었다.

   - ```java
     @FunctionalInterface
     public interface Runnable {
         public abstract void run();
     }
     ```

2. Lambda

   - 함수형 인터페이스는 람다를 이용하여 인스턴스화 될 수 있다. 화살표의 왼편은 입력이고 오른편은 코드이다. **입력타입은 추론 가능하기 때문에 선택**(하나의 abstract메서드만있어서)이다.

   - ```java
     (int x, int y) -> { return x + y; }
     (int x, int y) -> x + y
     (x,y) -> x + y
     x -> x * x
     () -> x
     x -> { System.out.pritln(x); }
     ```

3. Method Reference

   - 메서드 참조는 이름을 가진 메서드 들에 대한 컴팩트 한 람다 표현식이다.

   - ```jav
     String::valueOf
     x -> String.valueOf(x)
     
     Object::toString
     x -> x.toString()
     
     x::toString
     () -> x.toString()
     
     ArrayList::new
     () -> new ArrayList<>()
     ```

4. Closure

   - 람다는 람다 바디의 외부에 정의된 non-static변수 혹은 객체에 접근 가능하다. 이를 "capturing"이라고 한다.

   - 람다 표현식은 오직 로컬변수와 인자로 던져 감싸진 블록에서만 접근 가능하다.

   - ```java
     int x = 5;
     return y -> x + y;
     ```

5. Stream

   - java.util.stream패키지는 값들의 스트림 위에서 함수형-스타일의 동작을 지원하는 클래스들을 제공한다.

   - ```java
     int sumOfWeights = blocks.stream()
                        .filter(b -> b.getColor() == Red)
         			   .mapToInt(b -> b.getWeight())
         			   .sum();
     ```

   - 위의 예제는 스트림 위에서 filter-map-reduce를 수행한다.

   - 스트림은 먼저 어떤 소스로 부터 스트림을 얻고, 다음으로 하나 이상의 중간적 작업을 수행한후, 마지막으로 하나의 최종종료작업을 수행한다.

   - 중간 작업은 filter, map, flatMap, peel, distinct, sorted, limit, substream이다. 

   - 최종 작업은 forEach, toArray, reduce, collect, min, max, count, anyMatch, allMatch, noneMatch, findFirst, FindArray를 포함한다.



