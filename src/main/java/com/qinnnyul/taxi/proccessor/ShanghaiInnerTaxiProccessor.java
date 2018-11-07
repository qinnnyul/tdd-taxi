package com.qinnnyul.taxi.proccessor;

import com.qinnnyul.taxi.charger.CompositeTaxiCharger;
import com.qinnnyul.taxi.charger.TaxiCharger;

import java.math.BigDecimal;

import static com.qinnnyul.taxi.TaxiChargeFactory.BASE_DISTANCE;

public class ShanghaiInnerTaxiProccessor implements TaxiChargerProccessor{
    public static final BigDecimal INNER_DAY_TIME_BASE_PRICE = BigDecimal.valueOf(14);
    public static final BigDecimal INNER_DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.5);
    public static final BigDecimal INNER_DAY_TIME_EXTRA_PRICE_PER_MILE = BigDecimal.valueOf(3.5);
    public static final BigDecimal INNER_NIGTH_TIME_BASE_PRICE = BigDecimal.valueOf(18);
    public static final BigDecimal INNER_NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(3);
    public static final BigDecimal INNER_NIGHT_TIME_EXTRA_PRICE_PER_MILE = BigDecimal.valueOf(4.7);

    @Override
    public boolean isApplicable(String taxiType) {
        return "shangHaiInner".equals(taxiType);
    }

    @Override
    public TaxiCharger getDayTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(INNER_DAY_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, 10, INNER_DAY_TIME_PRICE_PER_MILE)
                .withAdditionalFee(10, Double.MAX_VALUE, INNER_DAY_TIME_EXTRA_PRICE_PER_MILE);
    }

    @Override
    public TaxiCharger getNightTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(INNER_NIGTH_TIME_BASE_PRICE).withAdditionalFee(BASE_DISTANCE, 10, INNER_NIGHT_TIME_PRICE_PER_MILE)
                .withAdditionalFee(10, Double.MAX_VALUE, INNER_NIGHT_TIME_EXTRA_PRICE_PER_MILE);
    }
}
