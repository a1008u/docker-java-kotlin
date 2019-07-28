import java.util.ArrayList;

class ClassSample<T>{
    private T t;

    public ClassSample(T t){
        this.t = t;
    }


    public T getT(){
        return t;
    }

    /**
     * 関数内で利用する型パラメータの記載位置について
     * 　メソッドの戻り値の型の手前に<T>を書くと、その型パラメータTをメソッド内で使用することができる。
     * @param arg
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> someMethod(T arg){
        ArrayList<T> list = new ArrayList<T>();
        list.add(arg);
        return list;
    }
}
