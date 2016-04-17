package lab03;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TestDishes {

	public static void main(String[] args) {
	    final List<Dish> menu =
	            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
	                           new Dish("beef", false, 700, Dish.Type.MEAT),
	                           new Dish("chicken", false, 400, Dish.Type.MEAT),
	                           new Dish("french fries", true, 530, Dish.Type.OTHER),
	                           new Dish("rice", true, 350, Dish.Type.OTHER),
	                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
	                           new Dish("pizza", true, 550, Dish.Type.OTHER),
	                           new Dish("prawns", false, 400, Dish.Type.FISH),
	                           new Dish("salmon", false, 450, Dish.Type.FISH));


	    // contare il numero di dish vegetariani
		long vegetarianDish = menu.stream()
				.filter(Dish::isVegetarian)
				.count();
		System.out.println("Number of vegetarian dishes : " + vegetarianDish);

	    // stampare in ordine crescente
	    // tutti i dish che hanno un numero di calorie comprese tra 400 e 600
		List<String> subMenu = menu.stream()
				.filter(dish -> dish.getCalories() >= 400 && dish.getCalories() <= 600)
				.sorted(comparing(Dish::getCalories))
                .map(Dish::getName) //si può anche togliere
				.collect(toList());

		System.out.println("Dish between 400 and 600 calories: " + subMenu);
	    
	    // Dato un pasto formato dalle pietanze specificate in myChoice (a seguire),
	    // determinare la quantità di calorie complessive del pasto
	    
	    List<String> myChoice = Arrays.asList("chicken","rice","season fruit");

        int total_calories = menu.stream()
                .filter(dish -> myChoice.contains(dish.getName())).mapToInt(Dish::getCalories).sum();
        System.out.println("Total calories: " + total_calories);
	}

}