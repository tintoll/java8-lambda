package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static stream.Dish.menu;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
    	List<Dish> vegeList = menu.stream()
    			                 						.filter(dish -> dish.isVegetarian())
    			                 						.collect(Collectors.toList());
    	vegeList.forEach(System.out::println);

    	System.out.println("================");
        // 2. Filtering unique elements
    	List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
    	numbers.stream().filter(i -> i % 2 == 0).distinct().collect(Collectors.toList()).forEach(System.out::println);
    	System.out.println("================");

        //3. Truncating 3 stream ( d.getCalories() > 300 )
    	List<Dish> dishesLimit3 = menu.stream().filter(dish -> dish.getCalories() > 300)
    														 .limit(3).collect(Collectors.toList());
    	dishesLimit3.forEach(System.out::println);
    	
    	System.out.println("================");
        //4. Skipping elements
    	List<Dish> dishesSkip2 = menu.stream().filter(dish -> dish.getCalories() > 300)
				 .skip(2).collect(Collectors.toList());
    	
    	dishesSkip2.forEach(System.out::println);
    	
    	
    	System.out.println("================");



    }
}
