package java8_inAction.java8_10;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class OperationsWithOptional {

    public static void main(String... args) {
        System.out.println(max(of(3), of(5)));

        // optionalを利用することで、nullpointerexceptionを防止する。
        System.out.println(max(empty(), of(5)));

        Optional<Integer> opt1 = of(5);
//        Optional<Integer> opt2 = opt1.orElse(() -> of(4));
//
        System.out.println(
                of(5).orElseGet(() -> 4)
        );
    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }
}
