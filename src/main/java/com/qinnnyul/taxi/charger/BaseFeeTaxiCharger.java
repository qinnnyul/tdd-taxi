package com.qinnnyul.taxi.charger;

import com.qinnnyul.taxi.model.Ride;

import java.math.BigDecimal;

public class BaseFeeTaxiCharger implements TaxiCharger{
    private BigDecimal basePrice;

    public BaseFeeTaxiCharger(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public BigDecimal chargeFee(Ride ride) {
        return ride.getDistance() == 0 ? BigDecimal.ZERO : basePrice;
    }
}
