# ジェネリクスの勉強
[Java ジェネリクスのポイント](https://qiita.com/pebblip/items/1206f866980f2ff91e77)  
[【Java・ジェネリックス】ワイルドカード型とは何か【前半】](https://www.thekingsmuseum.info/entry/2016/04/02/155821)  
[【Java・ジェネリックス】ワイルドカード型とは何か【後半】](https://www.thekingsmuseum.info/entry/2016/04/19/232836)  
[【Effective Java】項目２７：ジェネリックメソッドを利用する](https://www.thekingsmuseum.info/entry/2016/02/21/093900)  
[【Effective Java】項目２６：ジェネリック型を使用する](https://www.thekingsmuseum.info/entry/2016/02/14/152704)  
[ジェネリクスの基礎とクラス設計への応用](https://www.slideshare.net/nagise/jjug2013-public)   
[ジェネリクスの概論とか](https://www.slideshare.net/nagise/ss-77222069)  
[ジェネリクスの基礎と応用 JJUG CCC 2012 Fall](https://www.slideshare.net/nagise/jjug-ccc-2012-fall)  
[JavaのGenericsとは？](https://www.slideshare.net/nakamurakj/javagenerics)  
[]()  
[]()  
[]()  
[]()  
[]()  
[]()  
[]()  
[]()  


## ３種類の<>

    - 型変数の宣言
    - 型変数のバインド
    - パラメータ化された型

### 型変数の宣言 (宣言側)
```Java
class Hoge<T>{};
public <T> void hoge(T t){};
```

### 型変数のバインド (利用側)
```Java
// 正書法
new ArrayList<String>();
// ダイヤモンド演算子
new ArrayList<>();

class Hoge extends ArrayList<String>{};

// 正書法
Collections.<String>replaceAll(list,"hoge","hogehoge")
// 型推論
Collections.replaceAll(list,"hoge","hogehoge")
```

### パラメータ化された型 (利用側)
```Java
List<String> list;
```

----------------------------------

##境界ワイルドカード型について
サンプルクラス
```plantuml
digraph Test {
A -> B -> C -> B -> A;
A -> B1 -> A
}
```

### <? extends - >の入力制約
原則：add()できない。
```java
// bTestListはBを含むサブクラスをジェネリクスの対象とする。
List<? extends B> bTestList
new Hoge<A>(); -> NG
new Hoge<B>(); -> OK
new Hoge<B1>(); -> NG
new Hoge<C>(); -> OK
```

### <? super - >の出力制約
原則：get()はObject型としてしか返せない。
```java
// aTestListはBを含むスーパクラスをジェネリクスの対象とする。
List<? super B> aTestList
```


