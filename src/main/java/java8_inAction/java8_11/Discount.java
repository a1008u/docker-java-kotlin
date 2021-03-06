package java8_inAction.java8_11;

import static java8_inAction.java8_11.Util.delay;
import static java8_inAction.java8_11.Util.format;

public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        // 1秒待機
        delay();
        return format(price * (100 - code.percentage) / 100);
    }
}
