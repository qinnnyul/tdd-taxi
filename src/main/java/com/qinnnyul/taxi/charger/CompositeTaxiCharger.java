package com.qinnnyul.taxi.charger;

import com.qinnnyul.taxi.model.Ride;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompositeTaxiCharger implements TaxiCharger {

    private List<TaxiCharger> taxiChargers = new ArrayList<>();

    public CompositeTaxiCharger with(TaxiCharger taxiCharger) {
        taxiChargers.add(taxiCharger);
        return this;
    }

    public CompositeTaxiCharger withBaseFee(BigDecimal baseFee) {
        with(new BaseFeeTaxiCharger(baseFee));
        return this;
    }

    public CompositeTaxiCharger withAdditionalFee(double minMeter, double maxMeter, BigDecimal pricePerMeter) {
        with(new AdditionalFeeTaxiCharger(minMeter, maxMeter, pricePerMeter));
        return this;
    }

    @Override
    public BigDecimal chargeFee(Ride ride) {
        return taxiChargers.stream().map(taxiCharger -> taxiCharger.chargeFee(ride)).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
