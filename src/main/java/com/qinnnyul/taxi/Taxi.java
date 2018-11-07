package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class Taxi {

    public static final double BASE_DISTANCE = 3;
    public static final BigDecimal NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(13);
    public static final BigDecimal DAY_TIME_BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(1.6);
    public static final BigDecimal NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.4);

    public BigDecimal chargeFee(Ride ride) {
       return new TimeBasedTaxiCharger(new CompositeTaxiCharger().withBaseFee(DAY_TIME_BASE_PRICE).withAdditionalFee(DAY_TIME_PRICE_PER_MILE),
               new CompositeTaxiCharger().withBaseFee(NIGHT_TIME_BASE_PRICE).withAdditionalFee(NIGHT_TIME_PRICE_PER_MILE)).chargeFee(ride);
    }


}



