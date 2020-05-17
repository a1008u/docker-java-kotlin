# まとめ
- collectは、ストリームの要素をサマリー結果に蓄積するためのさまざまなレシピ (コレクターと呼ばれる) を引数に取るターミナル操作です。

- 定義済みのコレクタには、最小値、最大値、または平均値の計算など、ストリームの要素を1つの値に縮小して要約するものが含まれます。

- 定義済みコレクターを使用すると、ストリームの要素を groupingByでストリームをグループ化し、partitioningByでストリームの要素を分割します。

- コレクタは、効果的に構成して、複数レベルのグループ化、分割、および削減を作成します。

- コレクターインターフェイスで定義されているメソッドを実装することで、独自のコレクターを開発することができます。


Collectors クラスの機能
- ストリーム要素を単一の値に縮小してまとめる
- グループ化要素
- 要素を分割する


> import static java.util.stream.Collectors.*.


```java
/**
* T は、収集されるストリーム内のアイテムの一般的なタイプです。
* Aはアキュムレータのタイプであり、コレクション・プロセス中に部分的な結果が蓄積されるオブジェクトである。
* Rは、コレクト操作の結果として得られるオブジェクト(通常はコレクション)のタイプです。
*/
public interface Collector<T, A, R> {
    Supplier<A> supplier().
    BiConsumer<A, T>アキュムレータ().
    Function<A, R>finisher().
    BinaryOperator<A>コンビナー().
    Set<Characteristics>特性().
}

// 実装
public class ToListCollector<T> implements Collector<T, List<T>, List<T>>
```
