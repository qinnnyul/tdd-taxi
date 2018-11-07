package com.qinnnyul.taxi;

import java.math.BigDecimal;

public interface TaxiCharger {
    public BigDecimal chargeFee(Ride ride);
}
