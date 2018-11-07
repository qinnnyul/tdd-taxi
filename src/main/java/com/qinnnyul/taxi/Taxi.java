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
        if (isDay(ride)) {
            result = chargeDayFee(ride);
        } else {
            result = chargeNightFee(ride);
        }
        return result.setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    private boolean isDay(Ride ride) {
        return ride.getHourOfDay() >= 6 && ride.getHourOfDay() < 23;
    }


    public BigDecimal chargeDayFee(Ride ride) {
        BigDecimal result = BigDecimal.ZERO;

        BigDecimal basePrice = getBasePrice(ride, DAY_TIME_BASE_PRICE);
        result = result.add(basePrice);

        BigDecimal additionalPrice = getAdditionalPrice(ride, DAY_TIME_PRICE_PER_MILE);
        result = result.add(additionalPrice);

        return result;
    }

    public BigDecimal chargeNightFee(Ride ride) {
        BigDecimal result = BigDecimal.ZERO;

        BigDecimal basePrice = getBasePrice(ride, NIGHT_TIME_BASE_PRICE);
        result = result.add(basePrice);

        BigDecimal additionalPrice = getAdditionalPrice(ride, NIGHT_TIME_PRICE_PER_MILE);
        result = result.add(additionalPrice);

        return result;
    }


    private BigDecimal getBasePrice(Ride ride, BigDecimal basePrice) {
        return ride.getDistance() == 0 ? BigDecimal.ZERO : basePrice;
    }

    private BigDecimal getAdditionalPrice(Ride ride, BigDecimal pricePerMeter) {
        if (ride.getDistance()  <= BASE_DISTANCE) {
            return BigDecimal.ZERO;
        }
        double additionalDistance = ride.getDistance() - BASE_DISTANCE;
        return pricePerMeter.multiply(BigDecimal.valueOf(additionalDistance)
                .setScale(0, BigDecimal.ROUND_UP));
    }

}



