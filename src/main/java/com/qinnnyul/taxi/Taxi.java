package com.qinnnyul.taxi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<TaxiCharger> taxiChargers = Arrays.asList(new BaseFeeTaxiCharger(DAY_TIME_BASE_PRICE), new AdditionalFeeTaxiCharger(DAY_TIME_PRICE_PER_MILE));
        return taxiChargers.stream().map(taxiCharger -> taxiCharger.chargeFee(ride)).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal chargeNightFee(Ride ride) {
        List<TaxiCharger> taxiChargers = Arrays.asList(new BaseFeeTaxiCharger(NIGHT_TIME_BASE_PRICE), new AdditionalFeeTaxiCharger(NIGHT_TIME_PRICE_PER_MILE));
        return taxiChargers.stream().map(taxiCharger -> taxiCharger.chargeFee(ride)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}



