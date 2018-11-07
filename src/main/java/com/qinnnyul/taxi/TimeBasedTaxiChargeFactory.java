package com.qinnnyul.taxi;

import com.qinnnyul.taxi.charger.TaxiCharger;
import com.qinnnyul.taxi.charger.TimeBasedTaxiCharger;
import com.qinnnyul.taxi.config.TaxiConfig;
import com.qinnnyul.taxi.factory.TaxiChargerFactory;

import java.io.IOException;

public class TimeBasedTaxiChargeFactory {

    public TaxiCharger getTaxiCharger(String taxiType) throws IOException {
        TaxiConfig taxiConfig = new TaxiConfig("/taxi.properties");
        TaxiChargerFactory taxiChargerFactory = new TaxiChargerFactory(taxiConfig);

        return new TimeBasedTaxiCharger(taxiChargerFactory.getDayTaxiCharger(taxiType), taxiChargerFactory.getNightTaxiCharger(taxiType));

    }

}



