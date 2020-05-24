package java8_inAction.java8_13;

import java.util.stream.LongStream;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialStreams(5));
        System.out.println(factorialTailRecursive(5));
    }

    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r*=i;
        }
        return r;
    }

    // 再帰処理
    // 直接実行している数が多くなると、メモリを消費する
    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n*factorialRecursive(n-1);
    }

    // Streamの例
    public static long factorialStreams(long n){
        return LongStream
                .rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }

    // 再帰処理をする場合は、関数を呼び出しをすることでメモリの余分な利用を防げる
    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }
}
