package java8_inAction.java8_6;

import java8_inAction.Dish;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

// 6_2
public class Reducing {

    public static void main(String ... args) {

        // 下記２つは同じ結果となる
        long howManyDishes1 = Dish.menu.stream().collect(Collectors.counting());
        long howManyDishes2 = Dish.menu.stream().count();

        // 以下は全てカロリーの合計値を算出したものになるよ
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    // Collectors.maxBy
    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    //
    private static int calculateTotalCaloriesWithMethodReference() {
        return Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    //
    private static int calculateTotalCaloriesWithoutCollectors() {
        return Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
