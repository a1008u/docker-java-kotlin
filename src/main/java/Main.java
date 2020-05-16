import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static java.util.Locale.filter;
import static java.util.Locale.getISOCountries;

/**
 * 型パラメータについて
 * <E>
 *     Elementの略
 *     意味：要素 (Java コレクションフレームワークで広く用いられている)
 * </E>
 * <K>
 *     Keyの略
 *     意味：キー
 * </K>
 * <V>
 *     Valueの略
 *     意味：値
 * </V>
 * <N>
 *     Numberの略
 *     意味：数値
 * </N>
 * <T>
 *     Typeの略
 *     意味：
 * </T>
 * <S,U,V...>
 *     意味：二番目、三番目の型
 * </S,U,V...>
 */
public class Main {

    /**
     * 境界型パラメータ (Bounded Types)
     * <T extends Person> という表記で、「T は Person から派生したクラスです」と示し、処理で引数を利用できるようにする。
     * @param a
     * @param <T>
     */
    public static <T extends Person> void showName(T[] a){
        for(Person p : a){
            System.out.println("showName-----------");
            System.out.println(p.getName());
            System.out.println("-----------showName");
        }
    }

    /**
     * 非境界ワイルドカード型 (unbounded wildcard type)
     * @param list
     */
    static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.println("printList-----------");
            System.out.println(elem);
            System.out.println("-----------printList");
        }
    }

    public static void e(Lam l){
        l.run();
    }

    public static <T> void foreach(List<T> l, Consumer<T> cn){
        for(T ll : l){
            cn.accept(ll);
        }
    }

    public static <T, R> List<R> map(List<T> tList, Function<T,R> fn){
        List<R> resultList = new ArrayList<>();
        for(T t: tList){
            resultList.add(fn.apply(t));
        }
        return resultList;
    }

    public static void main(String[] args) {

        System.out.println("---------------");
        ErrorMethod em = new ErrorMethod();
        System.out.println(em.sampleLogic());
        System.out.println("---------------");

        // 無名関数
        Lam lam = new Lam() {
            @Override
            public void run() {
                System.out.println("test Lam");
            }
        };
        lam.run();

        // Lambda
        Lam lambda = () -> System.out.println("lambda lambda");
        lambda.run();
        e(lambda);

        List<String> testList = Arrays.asList("test1","test22","test333");
        Predicate<String> ckP = (String xxx) -> !xxx.isEmpty();

        String yyy = "ttttttt";
        Consumer<String> ckC = (String xxx) -> System.out.println(xxx);
        foreach(testList, ckC);

        // Functionインターフェースの例
        map(testList, (String t) -> t.length()).forEach(System.out::println);


        Supplier<SalesPerson> ssp = SalesPerson::new;
        SalesPerson spX = ssp.get();

        BiFunction<Integer, String, SalesPerson> sspp = SalesPerson::new;
        System.out.println(sspp.apply(1, "masa").toString());



        // String型として利用可能
        ClassSample<String> cs1 = new ClassSample<>("Hello");
        String str = cs1.getT();
        System.out.println(str);

        // Integer型として利用可能
        ClassSample<Integer> cs2 = new ClassSample<>(1);
        Integer i = cs2.getT();
        System.out.println(i);

        // 境界型パラメータ (Bounded Types)
        SalesPerson sp1 = new SalesPerson(1,"tom");
        SalesPerson sp2 = new SalesPerson(2,"ken");
        SalesPerson[] spList = new SalesPerson[2];
        spList[0] = sp1;
        spList[1] = sp2;
        showName(spList);

        // ワイルドカード型
        // 種類：
        //  1.非境界ワイルドカード型 (unbounded wildcard type) *List<?> list
        //  2.境界ワイルドカード型 (bounded wildcard type)
        //   2-1.上限境界ワイルドカード型 (upper bounded wildcard type) *List<? extends Number> list
        //   2-2.下限境界ワイルドカード型 (lower bounded wildcard type) *List<? super Number> list

        // 非境界ワイルドカード型 (unbounded wildcard type)
        // 役割：ジェネリックスを利用したいけど、型パラメータに何が含まれるか分からない場合に利用。
        // 問題点：
        // 　1.メソッドの戻り値に使われているTはObject型になる
        //   　理由：非境界ワイルドカード型は型パラメータが不明なため何が入っているか分からないが、
        //   　　　　すべての参照型のスーパータイプであるObjectなら絶対にClassCastExceptionは発生しないため
        // 　2.メソッドの引数に使われている T には null リテラルしか渡せない
        // 　　理由：非境界ワイルドカード型は型パラメータが不明なため setterの「this.value = value」の型が何か分からないが、
        // 　　　　　nullはどんな変数にも代入できるので ClassCastException は発生しない
        List<String> strings = new ArrayList<String>();
        strings.add("test1");
        strings.add("test2");
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(11);
        printList(strings);
        printList(integers);

        // Javaは不変であるが境界型ワイルドカード型を使うことで、一時的に変性を変えることができる。
        // PECS(Producer-Extends、Consumer-Super)
        // Producer、すなわちオブジェクトを生成するジェネリックス引数はextends（上限境界型ジェネリックス）を使うべきである。
        // Consumer、すなわちオブジェクトを消費するジェネリックス引数はsuper、（下限境界型ジェネリックス）を使うべきある。

        // 不変を一時的に、共変にする
        List<Integer> integerss = new ArrayList<Integer>();
        List<? extends Number> numbers = integerss;

        // 一時的に反変にする
        List<Object> objectsss = new ArrayList<Object>();
        List<? super Number> numbersss = new ArrayList<Object>();


        // 上限境界ワイルドカード型 (upper bounded wildcard type)
        // 役割：ワイルドカードを使うことでNumberを継承した派生クラスを全て利用対象にする。
        // 　　　一時的に共変にする
        ClassSample<? extends Number>  cs3 = cs2;
        Number n = cs3.getT();
        System.out.println(n);

        // 下限境界ワイルドカード型 (lower bounded wildcard type)
        // 役割：「このクラスのスーパークラスなら何でも」という指定を行う。
        // 　　　一時的に反変にする
        ClassSample<Number> cs4 = new ClassSample<>(1);
        ClassSample<? super Integer> cs5 = cs4;
        Object o = cs5.getT();
        System.out.println(o);

    }
}
