package com.qinnnyul.taxi.charger;

import com.qinnnyul.taxi.model.Ride;

import java.math.BigDecimal;

public interface TaxiCharger {
    public BigDecimal chargeFee(Ride ride);
}
