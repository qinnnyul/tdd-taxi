package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class Taxi {

    public static final double BASE_DISTANCE = 3;
    public static final BigDecimal NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(13);
    public static final BigDecimal DAY_TIME_BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(1.6);
    public static final BigDecimal NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.4);

    public BigDecimal chargeFee(Ride ride) {
        BigDecimal result;
        if (ride.getDistance() <= BASE_DISTANCE) {
            if (ride.getHourOfDay() >= 6 && ride.getHourOfDay() < 23) {
                result = DAY_TIME_BASE_PRICE;
            } else {
                result = NIGHT_TIME_BASE_PRICE;
            }

        } else {
            BigDecimal modifiedDistance = new BigDecimal(ride.getDistance()).setScale(0, BigDecimal.ROUND_UP);
            BigDecimal additionalDistance = modifiedDistance.subtract(BigDecimal.valueOf(BASE_DISTANCE));
            if (ride.getHourOfDay() >= 6 && ride.getHourOfDay() < 23) {
                result = DAY_TIME_BASE_PRICE.add(DAY_TIME_PRICE_PER_MILE.multiply(additionalDistance));
            } else {
                result = NIGHT_TIME_BASE_PRICE.add(NIGHT_TIME_PRICE_PER_MILE.multiply(additionalDistance));
            }
        }
        return result.setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
