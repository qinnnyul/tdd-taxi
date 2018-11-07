package com.qinnnyul.taxi;

import com.qinnnyul.taxi.charger.TaxiCharger;
import com.qinnnyul.taxi.config.TaxiConfig;
import com.qinnnyul.taxi.factory.TaxiChargerFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TaxiChargerFactoryTest {

    @Test
    public void shouldBuildDayTimeTaxiChargerBasedOnConfig() throws Exception {

        TaxiChargerFactory taxiChargerFactory = new TaxiChargerFactory(new TaxiConfig("/taxi.properties"));

        TaxiCharger taxiCharger = taxiChargerFactory.getDayTaxiCharger("normal");

        assertNotNull(taxiCharger);

    }


    @Test
    public void shouldBuildNightTimeTaxiChargerBasedOnConfig() throws Exception {

        TaxiChargerFactory taxiChargerFactory = new TaxiChargerFactory(new TaxiConfig("/taxi.properties"));

        TaxiCharger taxiCharger = taxiChargerFactory.getNightTaxiCharger("shangHaiInner");

        assertNotNull(taxiCharger);

    }


}
