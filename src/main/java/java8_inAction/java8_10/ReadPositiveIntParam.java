package java8_inAction.java8_10;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.*;

public class ReadPositiveIntParam {
//
//    @Test
//    public void testMap() {
//        Properties props = new Properties();
//        props.setProperty("a", "5");
//        props.setProperty("b", "true");
//        props.setProperty("c", "-3");
//
//        assertEquals(5, readDurationImperative(props, "a"));
//        assertEquals(0, readDurationImperative(props, "b"));
//        assertEquals(0, readDurationImperative(props, "c"));
//        assertEquals(0, readDurationImperative(props, "d"));
//
//        assertEquals(5, readDurationWithOptional(props, "a"));
//        assertEquals(0, readDurationWithOptional(props, "b"));
//        assertEquals(0, readDurationWithOptional(props, "c"));
//        assertEquals(0, readDurationWithOptional(props, "d"));
//    }


    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        System.out.println(readDurationImperative(props, "a"));
        System.out.println(readDurationImperative(props, "b"));
        System.out.println(readDurationImperative(props, "c"));
        System.out.println(readDurationImperative(props, "d"));

        System.out.println(readDurationWithOptional(props, "a"));
        System.out.println(readDurationWithOptional(props, "b"));
        System.out.println(readDurationWithOptional(props, "c"));
        System.out.println(readDurationWithOptional(props, "d"));
    }

    // optionalを利用しない場合
    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) { }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::s2i)
                .filter(i -> i > 0).orElse(0);
    }

    public static Optional<Integer> s2i(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }

}
