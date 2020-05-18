# まとめ
- ストリームとは、データ処理操作をサポートするソースからの要素のシーケンスです。
- ストリームは内部的な反復処理を利用します。反復処理は、フィルタ、マップ、ソートなどの操作によって抽象化されます。
- ストリームの操作には、中間操作と末端操作の2種類があります。
- filter や map のような中間操作はストリームを返し、連結することができます。これらの操作は、操作のパイプラインを設定するために使用されますが、結果は得られません。
- forEach や count などの末端演算は、ストリーム以外の値を返し、ストリームのパイプラインを処理して結果を返します。

ストリームの要素は、要求に応じて計算されます。

コレクションとストリームについて
- コレクションはデータについてのもの
- ストリームは計算についてのもの


