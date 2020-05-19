package java8_inAction.java8_5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 5_7
public class BuildingStreams {

    public static void main(String...args) throws Exception{

        // ストリームの生成：Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        System.out.println("--------------------");

        // ストリームの生成：Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // ストリームの生成（配列から）：Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());
        System.out.println("--------------------");

        // ストリームの生成：Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("--------------------");


        /**
         * 関数からストリームを生成する2つの静的メソッド
         * Stream.iterate
         * Stream.generate
         * これは、固定コレクションからストリームを作成するときのようにサイズが固定されていないストリームです。
         * このようなストリームでは、無限の値を表示しないように limit(n) を使用するのが一般的に賢明です。
         */

        // ストリームの生成：fibonnaci with iterate
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // public interface UnaryOperator<T> extends Function<T, T> { static <T> UnaryOperator<T> identity() { return t -> t; } }
        // Function<T, R> { R apply(T t); }
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        System.out.println("Stream.iterate--------------------");

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                . map(t -> t[0])
                .forEach(System.out::println);

        System.out.println("Stream.iterate--------------------");

        // ストリームの生成：random stream of doubles with Stream.generate
        // public static IntStream generate(IntSupplier s) {
        // public interface IntSupplier { int getAsInt(); }
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Stream.generate--------------------");

        // ストリームの生成：stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("Stream.generate--------------------");

        // 匿名クラスでIntSupplierを実装
        IntStream.generate(new IntSupplier(){
            public int getAsInt(){
                return 2;
            }
        }).limit(5).forEach(System.out::println);
        System.out.println("Stream.generate--------------------");



        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
        System.out.println("--------------------");


//        long uniqueWords = Files.lines(Paths.get("java/java8_inAction/java8_5/data.txt"), Charset.defaultCharset())
//                .flatMap(line -> Arrays.stream(line.split(" ")))
//                .distinct()
//                .count();
//        System.out.println("There are " + uniqueWords + " unique words in data.txt");


    }
}
