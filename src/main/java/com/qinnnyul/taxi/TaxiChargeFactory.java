package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class TaxiChargeFactory {

    public static final double BASE_DISTANCE = 3;
    public static final BigDecimal NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(13);
    public static final BigDecimal DAY_TIME_BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(1.6);
    public static final BigDecimal NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.4);

    public TaxiCharger getTaxiCharger() {
        CompositeTaxiCharger dayTaxiCharger = new CompositeTaxiCharger().withBaseFee(DAY_TIME_BASE_PRICE).withAdditionalFee(DAY_TIME_PRICE_PER_MILE);
        CompositeTaxiCharger nightTaxiCharger = new CompositeTaxiCharger().withBaseFee(NIGHT_TIME_BASE_PRICE).withAdditionalFee(NIGHT_TIME_PRICE_PER_MILE);
        return new TimeBasedTaxiCharger(dayTaxiCharger, nightTaxiCharger);
    }

}



