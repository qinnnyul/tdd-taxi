package com.qinnnyul.taxi.charger;

import com.qinnnyul.taxi.model.Ride;

import java.math.BigDecimal;

public interface TaxiCharger {
    BigDecimal chargeFee(Ride ride);
}
