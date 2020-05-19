package java8_inAction.java8_5;

import java8_inAction.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ステートレス
 *  map
 *  filter
 *
 * ステートフル
 *  reduce、
 *  sum
 *  max
 *  sorted
 *  distinct
 *
 *  ステートフルだと、要素が大きいと内部での保持するためのサイズが大きくなるので注意が必要
 *
 */
// 5_4
public class Reducing {

    public static void main(String...args){

        // T reduce(T identity, BinaryOperator<T> accumulator);
        // public interface BinaryOperator<T> extends BiFunction<T,T,T>
        // public interface BiFunction<T, U, R> { R apply(T t, U u); }
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        System.out.println("--------------------");

        // int sum2 = numbers.stream().reduce(0, (a,b) -> Integer.sum(a,b));
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        System.out.println("--------------------");

        // int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        int max = numbers.stream().reduce(0, Integer::max);
        System.out.println(max);

        System.out.println("--------------------");

        // Optional<Integer> min = numbers.stream().reduce( (i, ii) -> Integer.min(i, ii));
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        System.out.println("--------------------");

        int calories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
