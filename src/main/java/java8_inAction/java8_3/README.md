# まとめ
- ラムダ式は一種の匿名関数として理解することができます。
名前はありませんが、パラメータのリスト、ボディ、戻り値の型、そして投げられる例外のリストを持っています。

- ラムダ式を使うと、コードを簡潔に渡すことができます。

- 関数型インターフェイスとは、1つの抽象メソッドを正確に宣言するインターフェイスのことです。

- ラムダ式を使用できるのは、機能的なインターフェイスが期待される場合のみです。

- ラムダ式を使用すると、
機能インタフェースの抽象メソッドの実装を直接インラインで提供し、
式全体を機能インタフェースのインスタンスとして扱うことができます。

- Java8には、java.util .functionパッケージに共通の関数インタフェースのリストが含まれており、
Predicate<T>、Function<T, R>、Supplier<T>、Consumer<T>、およびBinaryOperator<T>が含まれています。

- Predicate<T>やFunction<T, R>のような一般的な汎用関数インタフェースの原始的な特殊化があり、
それらはボックス演算を避けるために使用することができます。IntPredicate、IntToLongFunctionなどです。

- execute aroundパターン（つまり、メソッド内で常に必要とされるコードの途中で、リソースの割り当てやクリーンアップなど、
ちょっとした動作を実行する必要があります）をラムダで使用することで、さらなる柔軟性と再利用性を得ることができます。

- ラムダ式に期待される型はターゲット型と呼ばれます。

- メソッド参照を使用すると、既存のメソッド実装を再利用して直接渡すことができます。

- コンパレータ、プレディケート、関数などの関数インタフェースには、ラムダ式を結合するために使用できるいくつかのデフォルトメソッドがあります。


## メソッド参照（Method references）

書き方
```java
class_name::method
```

種類は3つ
1.  静的メソッドへのメソッド参照（例えば、Integer::parseIntと書かれたIntegerクラスのparseIntメソッドなど）。
2.  任意の型のインスタンスメソッドへのメソッド参照（例えば、String::lengthと書かれたStringクラスのlengthメソッドなど）。
3.  既存のオブジェクトのインスタンスメソッドへのメソッド参照（例えば、Test型のインスタンスtのckResultメソッドなど）。
