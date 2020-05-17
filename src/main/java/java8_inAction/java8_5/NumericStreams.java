package java8_inAction.java8_5;

import java8_inAction.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// IntStream
// DoubleStream
// LongStream
// mapToInt、mapToDouble、および mapToLong 
public class NumericStreams {

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        // mapToIntを利用して、Stream<Integerではなく)IntStreamを返す
        int calories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Number of calories:" + calories);

        // IntStreamをStream<Integer>に変換する
        IntStream intStreamCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> streamCalories = intStreamCalories.boxed();

        System.out.println("--------------------");

        // max and OptionalInt
        // Optionalを利用すると、値の存在を確認できる。
        // IntStreamのsumメソッドだと値がない場合は0を返すため、存在がないことと0の区別ができない
        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = (maxCalories.isPresent()) ? maxCalories.getAsInt() : 1;
        System.out.println(max);

        System.out.println("--------------------");

        // numeric ranges -> rangeClosed(範囲の開始値を最初のパラメータ, 範囲の終了値を2番目のパラメータ)
        // 包括的 rangeClosed(1, 100) -> 1から100
        // 排他的 range(1, 100) -> 1から99
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        System.out.println("--------------------");

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

    public static boolean isPerfectSquare(int n){
        return Math.sqrt(n) % 1 == 0;
    }
}
