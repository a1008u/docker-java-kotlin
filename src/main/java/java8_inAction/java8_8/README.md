# まとめ
- ラムダ式を使用すると、コードをより読みやすく柔軟にすることができます。

- 匿名クラスをラムダ式に変換することを検討してください。
ただし、キーワード this の意味や変数のシャドウイングなどの微妙な意味の違いに注意してください。

- メソッド参照は、ラムダ式に比べてコードをより読みやすくすることができます。

- 反復的なコレクション処理を Streams API を使用するように変換することを検討してください。

- ラムダ式は、ストラテジー、テンプレート・メソッド、オブザーバー、責任の連鎖、ファクトリなど、
いくつかのオブジェクト指向の設計パターンに関連するボイラプレート・コードを削除するのに役立ちます。

- ラムダ式はユニットテストを行うこともできますが、一般的にはラムダ式が現れるメソッドの動作をテストすることに重点を置くべきです。

- 複雑なラムダ式を通常のメソッドに抽出することを検討してください。

- ラムダ式はスタックトレースを読みにくくする可能性があります。

- ストリームのpeekメソッドは、ストリームパイプラインの特定のポイントを通過する際に中間値をログに記録するのに便利です。


## lambdaへのリファクタの対象
- 匿名クラスをラムダ式にリファクタリング
- メソッド参照へのラムダ式のリファクタリング
- 命令型データ処理をストリームにリファクタリング

### 匿名クラスをラムダ式にリファクタリングの注意
1.thisの意味  
 - 匿名クラス内でthisは匿名クラス自体を参照します
 - ラムダ内ではそれを囲むクラスを参照します。

2.匿名クラスは、囲んでいるクラスから変数を隠すことができます。(ラムダ式ではできません) 

3.匿名クラスをラムダ式に変換すると、オーバーロードのコンテキストで結果のコードがあいまいになる可能性があります。
実際、匿名クラスのタイプはインスタンス化時に明示的ですが、ラムダのタイプはそのコンテキストに依存します。
