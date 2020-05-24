package java8_inAction.java8_14;

import java.util.function.Function;

// 14_5
public class Combinators {

    // High-order Functionsの例
    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }

    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    // n == 0 ? x -> x : compose((Integer x) -> 2 * x).apply(10), repeat(2, (Integer x) -> 2 * x).apply(10)));
    // n == 0 ? x -> x : compose((Integer x) -> 2 * x).apply(10), repeat(1, (Integer x) -> 2 * x).apply(10)));
    // n == 0 ? x -> x : compose((Integer x) -> 2 * x).apply(10), repeat(0, (Integer x) -> 2 * x).apply(10)));
    // x -> x
    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }
}
