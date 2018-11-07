package com.qinnnyul.taxi;

import java.math.BigDecimal;

public class AdditionalFeeTaxiCharger implements TaxiCharger {

    private final BigDecimal pricePerMeter;
    private final double minMeter;
    private final double maxMeter;

    public AdditionalFeeTaxiCharger(double minMeter, double maxMeter, BigDecimal pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
        this.minMeter = minMeter;
        this.maxMeter = maxMeter;
    }

    @Override
    public BigDecimal chargeFee(Ride ride) {
        if (ride.getDistance() <= minMeter) {
            return BigDecimal.ZERO;
        }
        double additionalDistance = Math.min(ride.getDistance(), maxMeter) - minMeter;
        return pricePerMeter.multiply(BigDecimal.valueOf(additionalDistance)
                .setScale(0, BigDecimal.ROUND_UP));
    }
}
