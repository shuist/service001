package com.bigFamily.service001;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestDemo {

    public static void main(String[] args) {
        double amountDouble = 3.14;

        BigDecimal amountBigDecimal = BigDecimal.valueOf(amountDouble);
        amountBigDecimal.setScale(3, RoundingMode.HALF_UP);

        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");

        System.out.println(df.format(amountDouble));
    }
}
