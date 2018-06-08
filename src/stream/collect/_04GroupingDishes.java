package stream.collect;

import stream.Dish;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;
import static stream.Dish.menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class _04GroupingDishes {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    //1. type별 그룹핑
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return null;
    }
    //2. 칼로리별 그룹핑
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return null;
    }

    //3. type별로 그룹핑 후에 다시 칼로리별로 그룹핑
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        // collect( groupingBy(type, groupingBy()) )
        return menu.stream()
            .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                if(dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if(dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                  return CaloricLevel.FAT;
                }
            })));
    }
    //4. type별 갯수 카운팅
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
    }
    //5. type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - reducing() 사용
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return null;
    }
    //5.1 type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - collectingAndThen() 사용
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));
        		    
    }

    //6. type별로 그룹핑하여 칼로리의 합계 내기
    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        toSet() )));
    }
}
