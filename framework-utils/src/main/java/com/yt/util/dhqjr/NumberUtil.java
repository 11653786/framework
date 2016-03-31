package com.yt.util.dhqjr;

import java.text.DecimalFormat;

public class NumberUtil {

    private static DecimalFormat df = new DecimalFormat("#0.00");

    public static Double twoDecimalPlaces(Double num) {

        return Double.parseDouble(df.format(num));
    }

}
