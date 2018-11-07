package com.qinnnyul.taxi;

import java.math.BigDecimal;

import static com.qinnnyul.taxi.TaxiChargeFactory.BASE_DISTANCE;

public class AdditionalFeeTaxiCharger implements TaxiCharger {

    private final BigDecimal pricePerMeter;

    public AdditionalFeeTaxiCharger(BigDecimal pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }

    @Override
    public BigDecimal chargeFee(Ride ride) {
        if (ride.getDistance() <= BASE_DISTANCE) {
            return BigDecimal.ZERO;
        }
        double additionalDistance = ride.getDistance() - BASE_DISTANCE;
        return pricePerMeter.multiply(BigDecimal.valueOf(additionalDistance)
                .setScale(0, BigDecimal.ROUND_UP));
    }
}
