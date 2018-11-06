package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class Taxi {

    public static final double BASE_DISTANCE = 3;
    public static final BigDecimal BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal PRICE_PER_MILE = BigDecimal.valueOf(1.6);

    public BigDecimal chargeFee(double distance) {
        BigDecimal result;
        if (distance <= BASE_DISTANCE) {
            result = BASE_PRICE;
        } else {
            BigDecimal modifiedDistance = new BigDecimal(distance).setScale(0, BigDecimal.ROUND_UP);
            BigDecimal additionalDistance = modifiedDistance.subtract(BigDecimal.valueOf(BASE_DISTANCE));
            result = BASE_PRICE.add(PRICE_PER_MILE.multiply(additionalDistance));
        }
        return result.setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
