package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class TaxiChargeFactory {

    public static final double BASE_DISTANCE = 3;
    public static final BigDecimal NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(13);
    public static final BigDecimal DAY_TIME_BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(1.6);
    public static final BigDecimal NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.4);
    public static final BigDecimal OUTER_DAY_TIME_BASE_PRICE = BigDecimal.valueOf(14);
    public static final BigDecimal OUTER_DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.5);
    public static final BigDecimal OUTER_NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(18);
    public static final BigDecimal OUTER_NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(3);
    public static final BigDecimal INNER_DAY_TIME_BASE_PRICE = BigDecimal.valueOf(14);
    public static final BigDecimal INNER_DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.5);
    public static final BigDecimal INNER_DAY_TIME_EXTRA_PRICE_PER_MILE = BigDecimal.valueOf(3.5);
    public static final BigDecimal INNER_NIGTH_TIME_BASE_PRICE = BigDecimal.valueOf(18);
    public static final BigDecimal INNER_NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(3);
    public static final BigDecimal INNER_NIGHT_TIME_EXTRA_PRICE_PER_MILE = BigDecimal.valueOf(4.7);

    public TaxiCharger getTaxiCharger(String taxiType) {
        CompositeTaxiCharger dayTaxiCharger = new CompositeTaxiCharger();
        CompositeTaxiCharger nightTaxiCharger = new CompositeTaxiCharger();

        if ("normal".equals(taxiType)) {
             dayTaxiCharger.withBaseFee(DAY_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, DAY_TIME_PRICE_PER_MILE);
             nightTaxiCharger.withBaseFee(NIGHT_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, NIGHT_TIME_PRICE_PER_MILE);
        }

        if("shangHaiInner".equals(taxiType)) {
            dayTaxiCharger.withBaseFee(INNER_DAY_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, 10, INNER_DAY_TIME_PRICE_PER_MILE)
                    .withAdditionalFee(10, Double.MAX_VALUE, INNER_DAY_TIME_EXTRA_PRICE_PER_MILE);
            nightTaxiCharger.withBaseFee(INNER_NIGTH_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, 10, INNER_NIGHT_TIME_PRICE_PER_MILE)
                    .withAdditionalFee(10, Double.MAX_VALUE, INNER_NIGHT_TIME_EXTRA_PRICE_PER_MILE);
        }

        if("shangHaiOuter".equals(taxiType)) {
            dayTaxiCharger.withBaseFee(OUTER_DAY_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, OUTER_DAY_TIME_PRICE_PER_MILE);
            nightTaxiCharger.withBaseFee(OUTER_NIGHT_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, OUTER_NIGHT_TIME_PRICE_PER_MILE);
        }
        return new TimeBasedTaxiCharger(dayTaxiCharger, nightTaxiCharger);

    }

}



