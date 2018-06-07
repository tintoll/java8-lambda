package lambda.functional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilteringApples {

	public static void main(String... args) {

		List<Apple> inventory = 
				Arrays.asList(new Apple(80, "green"), 
							  new Apple(155, "green"), 
							  new Apple(120, "red"));

		// filter method 호출
		// 1. 익명클래스 사용
		 filter(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return apple.getColor().equals("green");
			}
		});
		 // 2. 람다식 사용
		 filter(inventory, apple -> apple.getColor().equals("green"));
		 
		 
		 // fileter2 method  활용 
		 filter2(inventory, new Predicate<Apple>() {
			@Override
			public boolean test(Apple apple) {
				return apple.getWeight() > 150;
			}
		});
		 
		 // 메소드 레퍼런스 사용 
		 filter2(inventory, apple -> apple.getWeight() > 150).forEach(System.out::println);
		 filter2(inventory, apple -> apple.getWeight() > 150).forEach(apple -> System.out.println(apple.toString()));
		
		 
		 // Consumer를 익명클래스로 사용
		 printAppleInfo(inventory, new Consumer<Apple>() {
			@Override
			public void accept(Apple apple) {
				System.out.println(apple.toString());
			}
		});
		// lambda 
		printAppleInfo(inventory, apple -> System.out.println(apple)); 
		printAppleInfo(inventory, System.out::println);
		 
	}


	// Consumer 인터페이스를 인자로 받는 printAppleInfo
	public static void printAppleInfo(List<Apple> inventory, Consumer<Apple> consumer) {
		for (Apple apple : inventory) {
			consumer.accept(apple);
		}
	}
	
	// Predicate 인터페이스를 인자로받는 filter2
	public static List<Apple> filter2(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	@FunctionalInterface
	interface ApplePredicate {
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}
}