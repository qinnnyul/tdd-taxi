package com.qinnnyul.taxi.charger;

import com.qinnnyul.taxi.model.Ride;

import java.math.BigDecimal;

public class TimeBasedTaxiCharger implements TaxiCharger {
    private TaxiCharger dayTaxiCharger;
    private TaxiCharger nightTaxiCharger;

    public TimeBasedTaxiCharger(TaxiCharger dayTaxiCharger, TaxiCharger nightTaxiCharger) {
        this.dayTaxiCharger = dayTaxiCharger;
        this.nightTaxiCharger = nightTaxiCharger;
    }

    @Override
    public BigDecimal chargeFee(Ride ride) {
        return isDay(ride) ? this.dayTaxiCharger.chargeFee(ride).setScale(1, BigDecimal.ROUND_HALF_UP) : this.nightTaxiCharger.chargeFee(ride).setScale(1, BigDecimal.ROUND_HALF_UP);

    }


    private boolean isDay(Ride ride) {
        return ride.getHourOfDay() >= 6 && ride.getHourOfDay() < 23;
    }
}
