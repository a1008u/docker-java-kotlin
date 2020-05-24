package java8_inAction.java8_14;

// 14_2_2
public class PersistentTree {

    public static void main(String[] args) {

        /**
         * Treeのイメージ
         * [Alan Emily Georgie] -  Mary - [Raoul Tian]
         */
        Tree t = new Tree("Mary", 22,
                new Tree(
                        "Emily", 20,
                        new Tree("Alan", 50, null, null),
                        new Tree("Georgie", 23, null, null)
                ),
                new Tree(
                        "Tian", 29,
                        new Tree("Raoul", 23, null, null),
                        null
                )
        );

        //　通常の探索
        // found = 23
        System.out.println(lookup("Raoul", -1, t));
        // not found = -1
        System.out.println(lookup("Jeff", -1, t));

        // function的に実装されたupdateの実行
        Tree f = fupdate("Jeff", 80, t);
        // not found = -1
        System.out.println(lookup("Jeff", -1, f));

        Tree u = update("Jim", 40, t);
        // t was not altered by fupdate, so Jeff is not found = -1
        System.out.println(lookup("Jeff", -1, u));
        // found = 40
        System.out.println(lookup("Jim", -1, u));

        Tree f2 = fupdate("Jeff", 80, t);
        // found = 80
        System.out.println(lookup("Jeff", -1, f2));
        // f2 built from t altered by update() above, so Jim is still present = 40
        System.out.println(lookup("Jim", -1, f2));
    }

    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String k, int v, Tree l, Tree r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }
    }

    public static int lookup(String k, int defaultval, Tree t) {
        if (t == null)
            return defaultval;
        if (k.equals(t.key))
            return t.val;
        return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
    }

    //　副作用あり
    public static Tree update(String k, int newval, Tree t) {
        if (t == null)
            t = new Tree(k, newval, null, null);
        else if (k.equals(t.key))
            t.val = newval;
        else if (k.compareTo(t.key) < 0)
            t.left = update(k, newval, t.left);
        else
            t.right = update(k, newval, t.right);
        return t;
    }

    // 副作用なし
    public static Tree fupdate(String k, int newval, Tree t) {
        return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.key) ?
                        new Tree(k, newval, t.left, t.right) :
                        k.compareTo(t.key) < 0 ?
                                new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :
                                new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
    }

}
