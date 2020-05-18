package java8_inAction.java8_5;

import java8_inAction.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

// 5_2
// map() => 「変更する」ではなく「新しいバージョンを作成する」
public class Mapping{

    public static void main(String...args){

        // map
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);
        System.out.println("-----------------------------");

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                // .map(w -> w.length())
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        System.out.println("-----------------------------");

        /**
         * flatMapの例
         */

        // flatMapを使わずにMapを利用した例
        // mapが返すのはStream<String[]>となるが、本当に欲しいのはStream<String>
        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .forEach(System.out::println);
        System.out.println("-----");

        // flatMapの作成過程1
        // Arrays.stream()というメソッドがあり、これは配列を受け取ってストリームを生成します。
        // ですが、まだこれではダメです。理由は「Stream<Stream<String>>」となってしまうからです。
        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        System.out.println("------------");

        // flatMapの作成過程2
        // 各単語を個々の文字の配列に変換してから、それぞれの配列を別々のストリームにします。
        //flatMapメソッドは、ストリームの各値を別のストリームに置き換え、生成されたすべてのストリームを1つのストリームに連結します。
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        System.out.println("--------------------");

        // flatMapの作成完了
        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);

        System.out.println("-----------------------------");

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs = numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[]{i, j}))
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
