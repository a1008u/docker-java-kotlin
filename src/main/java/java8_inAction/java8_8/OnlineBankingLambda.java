package java8_inAction.java8_8;

import java.util.function.Consumer;

// 8_2.2
// テンプレートメソッドパターン
// - このアルゴリズムを使用したいのですが、数行を変更して必要なことを実行する必要がある
public class OnlineBankingLambda {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello2222!"));
    }

    // この処理でメインでやりたいこと
    // これをclassを増やすのではなくて、複数実行する
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    static private class Customer {}
    // dummy Database class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }
}
