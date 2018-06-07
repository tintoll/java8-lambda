package stream;
import java.util.Optional;

import static stream.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return Dish.menu.stream().filter(dish -> dish.getCalories() <= 200).anyMatch(Dish::isVegetarian);
    }
    //2.allMatch
    private static boolean isHealthyMenu(){
        return Dish.menu.stream().allMatch(dish -> dish.getCalories() < 900);
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){
        return Dish.menu.stream().noneMatch(dish -> dish.getCalories() > 900);
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){
        return Dish.menu.stream().filter(dish -> dish.getCalories() > 300).findAny();
    }
    
}
