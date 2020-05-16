package java8_inAction.java8_5;

import java8_inAction.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

// 5_1
// filterは（ブール値を返す関数）を取り、述語にマッチするすべての要素を含むストリームを返します。
public class Filtering{

    public static void main(String...args){

        // Filtering with predicate
        List<Dish> vegetarianMenu =
                Dish.menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        System.out.println("-----------------------------");

        // distinct()の例
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("-----------------------------");

        // limit()の例
        List<Dish> dishesLimit3 =
                Dish.menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .limit(3)
                        .collect(toList());
        dishesLimit3.forEach(System.out::println);

        System.out.println("-----------------------------");

        // skip()の例：指定した数スキップする。
        List<Dish> dishesSkip2 =
                Dish.menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());

        dishesSkip2.forEach(System.out::println);
    }
}
