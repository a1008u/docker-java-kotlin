package java8_inAction.java8_3;

import java8_inAction.Dish;

import java.util.function.Supplier;

public class exec {

    // 引数なしのコンストラクターの場合
    Supplier<Dish> d = Dish::new;

    //
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
