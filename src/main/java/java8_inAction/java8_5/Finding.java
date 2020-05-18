package java8_inAction.java8_5;

import java8_inAction.Dish;

import java.util.Optional;

// 5_3
// anyMatch = ||
// allMatch = &&
// noneMatch <-> allMatch = &&
// findAny
// findFirst
//
// Short-circuiting evaluation
// ストリーム内のすべての要素を処理することなく、指定されたサイズのストリームを作成するだけで済みます。このような操作は、無限サイズのストリームを扱う必要がある場合などに便利です。
public class Finding{

    // データの集合内のいくつかの要素が与えられたプロパティに一致するかどうかを見つけることです。
    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());

        // ifPresent() は Optional に値が含まれている場合は true を返し、そうでない場合は false を返します。
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    // anyMatchメソッドは、"与えられた述語にマッチする要素がストリーム内にあるか？" という質問に答えるために使用できます
    // boolean値を返すため、終端処理
    private static boolean isVegetarianFriendlyMenu(){
        return Dish.menu.stream().anyMatch(Dish::isVegetarian);
    }

    // allMatch メソッドは anyMatch と同様に動作しますが、ストリームのすべての要素が与えられた述語にマッチするかどうかをチェックします。
    private static boolean isHealthyMenu(){
        return Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    // allMatch メソッドの真逆
    private static boolean isHealthyMenu2(){
        return Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    // findAny メソッドは、現在のストリームの任意の要素を返します。
    private static Optional<Dish> findVegetarianDish(){
        return Dish.menu.stream().filter(Dish::isVegetarian).findAny();
    }

}
