package stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("------------------------");
        // Java 8
        // Iterable 인터페이스의 forEach(Comsumer)사용
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
        
        System.out.println("------------------------");
        System.out.println(getGroupingMenu(Dish.menu));

        System.out.println("------------------------");
        System.out.println(getMaxCaloryDish(Dish.menu));
        
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
    	/*
    	 	1. stream() : Stream<Dish>
    	 	2. filter() : Stream<Dish>
    	 	3. sorted() : Stream<Dish>
    	    4. map() : Stream<String>
    	    5. collect() : List<String>
    	    6. subList() : List<String>
    	 */
    	return dishes.stream()
    						   .filter(dish -> dish.getCalories() <= 400)
    						  // .sorted(Comparator.comparing(Dish::getCalories))
    						  // .map(Dish::getName)
    						   .sorted(comparing(dish -> dish.getCalories())) // static  import 해서 사용할수있다.
    						   .map(dish -> dish.getName())
    						   .collect(toList())
    						   .subList(0, 3);
    }
    
    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
    	
    	/*
    	 *   Map<K, List<T>  groupingBy(Function<T, K>)
    	 */
    	return dishes.stream()
    						   .collect(groupingBy(dish -> {
    							   if(dish.getCalories() <= 400 ) {
    								   return "diet";
    							   }else {
    								   return "normal";
    							   }
    						   }));
 
    }
    
 
    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
    	/*
    	 * 1. steam() : Stream<Dish>
    	 *  2. max() : Optional<Dish>
    	 *  3. get() : Dish
    	 */
    	return dishes.stream()
    						   .max(Comparator.comparingInt(Dish::getCalories)).get();
    	
    	
    }
}
