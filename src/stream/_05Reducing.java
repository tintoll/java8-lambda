package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class _05Reducing {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
    	
        // reduce - sum을 구현하는 방법
        int sum = numbers.stream().reduce(0,(a,b) -> a+b);
        sum = numbers.stream().reduce(0, (a,b) -> Integer.sum(a,b));
        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // 최소값 구하기
        Optional<Integer> optVal = numbers.stream().reduce(Integer::min);
        optVal.ifPresent(System.out::println); // 값이 있으면 출력
        int min = optVal.get();
        System.out.println(min);

        // 최대값 구하기
        int max = numbers.stream().reduce(0,Integer::max);
        System.out.println(max);

        // 칼로리 합계를 구하는 여러가지 방법 -3번방식을 권장
        // 1. reduce()함수를 직접 구현
        int sumCalory = Dish.menu.stream()
                            .map(dish -> dish.getCalories())
                            .reduce(0, (d1,d2) -> d1+d2);
        System.out.println(sumCalory);
        // 2. reduce()에서 Integer::sum 호출
        sumCalory = Dish.menu.stream()
                .map(dish -> dish.getCalories())
                .reduce(0, Integer::sum);
        System.out.println(sumCalory);
        // 3. reduce() 대신 mapToInt()를 사용해서 IntStream으로 변환후 sum()호출
        sumCalory = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sumCalory);
        
    }
}
