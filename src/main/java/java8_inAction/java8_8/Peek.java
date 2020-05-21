package java8_inAction.java8_8;

import java.util.List;
import java.util.stream.Stream;

// 8_4.2
// peekでstreamのロギング
// ストリームの各要素が消費されるときにアクションを実行することです。
import static java.util.stream.Collectors.toList;

public class Peek {

    public static void main(String[] args) {
        List<Integer> result = Stream.of(2, 3, 4, 5)
                .peek(x -> System.out.println("taking from stream: " + x)).map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)).limit(3)
                .peek(x -> System.out.println("after limit: " + x)).collect(toList());
    }
}
