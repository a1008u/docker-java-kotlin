package java8_inAction.java8_11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComletableFutureSample1 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // CompletableFutureの実装です。引数には、Supplier、ExecutorServiceを取ります。
        // Supplierはめんどくさいのでラムダ式で書いています。
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("スレッドの処理開始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("スレッドの処理だん");
            return "結果が出ました";
        }, executor);

        // CompletableFutureで定義したSupplierを実行します。
        // whenCompleteはBiConsumerを引数に取るメソッドで、
        // retに戻り値、errに例外(Throwable)が入ってきます。
        cf.whenComplete((ret, err) -> {
            System.out.println(ret);
        });
        System.out.println("メインの処理だん");
    }
}
