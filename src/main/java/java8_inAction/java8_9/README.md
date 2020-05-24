# default method
- Java 8のインターフェースは、デフォルトのメソッドと静的メソッドを介して実装コードを持つことができます。
- デフォルトのメソッドはdefaultキーワードで始まり、クラスメソッドのように本文が含まれています。
- 公開メソッドに抽象メソッドを追加することは、ソースの非互換性です。
- デフォルトのメソッドは、ライブラリ設計者が下位互換性のある方法でAPIを進化させるのに役立ちます。
- デフォルトのメソッドを使用して、オプションのメソッドと動作の多重継承を作成できます。
- クラスが同じシグネチャを持ついくつかのデフォルトメソッドから継承する場合の競合を解決するための解決ルールがあります。
- クラスまたはスーパークラスのメソッド宣言は、デフォルトのメソッド宣言よりも優先されます。
それ以外の場合は、最も具体的なデフォルト提供インターフェースで同じシグニチャーを持つメソッドが選択されます。 
- 2つのメソッドが等しく特定されている場合、クラスはメソッドを明示的にオーバーライドして、呼び出すメソッドを選択できます。

## interfaceにmethodを追加する方法
1.Java8では、インターフェース内で静的メソッドを使用できます。
2.Java8にはデフォルトメソッドと呼ばれる新機能が導入されていますこれにより、インターフェースのメソッドにデフォルトの実装を提供できます。

＞デフォルトのメソッドの主なユーザーは、ライブラリデザイナーです。


## Java 8の抽象クラスとインターフェイス
- 抽象クラス
> クラスは1つの抽象クラスからのみ拡張できます。
>　抽象クラスはインスタンス変数（フィールド）を通じて共通の状態を強制できます。
- インターフェイス
>　クラスは複数のインターフェースを実装できます。
>　インターフェイスにインスタンス変数を含めることはできません。
  
## 継承は有害と見なされます
デフォルトのメソッドを持つインターフェースにも適用できます。  
インターフェースを最小限に保つことにより、必要な実装のみを選択できるため、より優れた構成を実現できます。