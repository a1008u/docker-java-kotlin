package java8_inAction.java8_3;

import java8_inAction.Dish;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

public class ExecuteAround {

    public static void main(String ...args) throws IOException {

        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        // lambdaをinterfaceにDIするイメージ
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);

    }

    // try-with-resourcesを利用
    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
            return br.readLine();
        }
    }


    //　lambdaを利用した場合
    // 考え方としてはprocessFileLimited()をやっているので、同じことをしている
    // lambdaはFunctionalInterfaceのコンテキストでのみ使用できる
    // lambdaは、関数インタフェースの抽象メソッドの実装を直接インラインで提供し、式全体を関数インタフェースのインスタンスとして扱うことを覚えておいてください。
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))){
            return p.process(br);
        }
    }

}

@FunctionalInterface
interface BufferedReaderProcessor{
    public String process(BufferedReader b) throws IOException;
}
