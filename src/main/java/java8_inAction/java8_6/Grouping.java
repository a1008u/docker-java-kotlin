package java8_inAction.java8_6;

import java8_inAction.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.*;

// 6_3
public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("groupDishesByType()--------------------");
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
        System.out.println("groupDishNamesByType()--------------------");
//        System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
//        System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("groupDishesByCaloricLevel()--------------------");
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("groupDishedByTypeAndCaloricLevel()--------------------");
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("countDishesInGroups()--------------------");
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("mostCaloricDishesByType()--------------------");
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOprionals());
        System.out.println("mostCaloricDishesByTypeWithoutOprionals()--------------------");
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("sumCaloriesByType()--------------------");
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }

//    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
//        return Dish.menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> Dish.dishTags.get( dish.getName() ).stream(), toSet())));
//    }
//
//    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
////        return menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
//        return Dish.menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
//    }

    /**
     * 400カロリー以下の料理をすべて "diet "に分類し、
     * 400～700カロリーの料理を "normal "に設定し、
     * 700カロリー以上の料理を "fat "に設定する
     * @return
     */
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));
    }

    /**
     * まず、Type別に分けて、その中で
     * 400カロリー以下の料理をすべて "diet "に分類し、
     * 400～700カロリーの料理を "normal "に設定し、
     * 700カロリー以上の料理を "fat "に設定する
     * @return
     */
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy((Dish dish) -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        } )
                )
        );
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    /**
     * まず、Type別に分けて、その中で各グループの最大カロリーを求める
     * @return
     */
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    /**
     * まず、Type別に分けて、その中で各グループの最大カロリーを求める（Mapのvalueにoptionalを付けない）
     * @return
     */
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return Dish.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    /**
     * まず、Type別に分けて、その中で各グループのカロリーレベルを探索して、setとしてまとめる
     * @return
     */
    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(
                            dish -> {
                                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT;
                                },
                        toSet() )));
    }
}
