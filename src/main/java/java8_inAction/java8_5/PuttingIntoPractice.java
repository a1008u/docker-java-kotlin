package java8_inAction.java8_5;

import java8_inAction.java8_5.domain.Trader;
import java8_inAction.java8_5.domain.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice{
    public static void main(String ...args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        // 2011年のすべての取引を検索し、金額が小さいものから大きいものまで並べ替えます。
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(tr2011);

        // Query 2: What are all the unique cities where the traders work?¥
        // トレーダーが働いているユニークな都市はどれか?
        List<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        // ケンブリッジのすべてのトレーダーを検索し、名前でソートします。
        List<Trader> traders =
                transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);


        // Query 4: Return a string of all traders’ names sorted alphabetically.
        // すべてのトレーダーの名前をアルファベット順に並べた文字列を返します。
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        // Query 5: Are there any trader based in Milan?
        // ミラノに拠点を置くトレーダーはいますか？
        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan")
                        );
        System.out.println(milanBased);


        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        // ケンブリッジに住んでいるトレーダーのすべての取引の値を表示します。
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);


        // Query 7: What's the highest value in all the transactions?
        // すべての取引の中で最も高い値は何ですか？
        int highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);

        // Find the transaction with the smallest value.
        // 値が最も小さい取引を探します。
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .min(comparing(Transaction::getValue));
        smallestTransaction.ifPresent(System.out::println);
    }
}
