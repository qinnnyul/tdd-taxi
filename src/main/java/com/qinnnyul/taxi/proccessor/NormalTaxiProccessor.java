package com.qinnnyul.taxi.proccessor;

import com.qinnnyul.taxi.charger.CompositeTaxiCharger;
import com.qinnnyul.taxi.charger.TaxiCharger;

import java.math.BigDecimal;

import static com.qinnnyul.taxi.TaxiChargeFactory.BASE_DISTANCE;

public class NormalTaxiProccessor implements TaxiChargerProccessor{
    public static final BigDecimal NIGHT_TIME_BASE_PRICE = BigDecimal.valueOf(13);
    public static final BigDecimal DAY_TIME_BASE_PRICE = BigDecimal.valueOf(11);
    public static final BigDecimal DAY_TIME_PRICE_PER_MILE = BigDecimal.valueOf(1.6);
    public static final BigDecimal NIGHT_TIME_PRICE_PER_MILE = BigDecimal.valueOf(2.4);

    @Override
    public boolean isApplicable(String taxiType) {
        return "normal".equals(taxiType);
    }

    @Override
    public TaxiCharger getDayTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(DAY_TIME_BASE_PRICE)
                .withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, DAY_TIME_PRICE_PER_MILE);
    }

    @Override
    public TaxiCharger getNightTaxiCharger() {
        return new CompositeTaxiCharger().withBaseFee(NIGHT_TIME_BASE_PRICE)
                .withAdditionalFee(BASE_DISTANCE, Double.MAX_VALUE, NIGHT_TIME_PRICE_PER_MILE);
    }
}
