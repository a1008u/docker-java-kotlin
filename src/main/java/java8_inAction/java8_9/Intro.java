package java8_inAction.java8_9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 9_1
public class Intro{

    public static void main(String...args){
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        // sort is a default method
        // naturalOrder is a static method
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);

        TestSample t = new TestSample();
        t.testSay();
    }
}

/**
 * もし継承関係の中で、同じメソッドが存在する場合は、下記のルールに依存します。
 * 1.まず、クラスまたはスーパークラスでの明示的なメソッド宣言は、デフォルトのメソッド宣言よりも優先されます。
 * 2.それ以外の場合は、最も具体的なデフォルト提供インターフェースで同じシグニチャーを持つメソッドが選択されます。
 * 3.最後に、まだ競合がある場合は、デフォルトのメソッドを明示的にオーバーライドして、クラスが使用するメソッドを選択する必要があります。
 */
class TestSample implements I_sample1, I_sample2{
    public void testSay(){
        new TestSample().say();
    }

    // defaultで同じmethodを指定すると、どちらを使用していいか分からないので、
    // overrideが必要になる。
    @Override
    public void say() { }
}
