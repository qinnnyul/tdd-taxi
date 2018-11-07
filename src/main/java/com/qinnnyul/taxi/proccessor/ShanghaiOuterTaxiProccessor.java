package com.qinnnyul.taxi.proccessor;

import com.qinnnyul.taxi.charger.CompositeTaxiCharger;
import com.qinnnyul.taxi.charger.TaxiCharger;

import java.math.BigDecimal;

import static com.qinnnyul.taxi.TaxiChargeFactory.BASE_DISTANCE;

public class ShanghaiOuterTaxiProccessor implements TaxiChargerProccessor{
    public static final BigDecimal OUTER_DAY_TIME_BASE_PRICE = BigDecimal.valueOf(14);
    public static final BigDecimal OUTER_DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.5);
    public static final BigDecimal OUTER_NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(18);
    public static final BigDecimal OUTER_NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(3);

    @Override
    public boolean isApplicable(String taxiType) {
        return "shangHaiOuter".equals(taxiType);
    }

    @Override
    public TaxiCharger getDayTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(OUTER_DAY_TIME_BASE_PRICE)
                .withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, OUTER_DAY_TIME_PRICE_PER_MILE);
    }

    @Override
    public TaxiCharger getNightTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(OUTER_NIGHT_TIME_BASE_PRICE)
                .withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, OUTER_NIGHT_TIME_PRICE_PER_MILE);
    }
}
