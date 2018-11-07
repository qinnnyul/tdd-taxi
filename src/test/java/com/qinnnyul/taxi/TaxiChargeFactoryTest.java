package com.qinnnyul.taxi;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TaxiChargeFactoryTest {

    private TaxiCharger taxiCharger;

    @Before
    public void setUp() throws Exception {
        taxiCharger = new TaxiChargeFactory().getTaxiCharger();

    }

    @Test
    public void shouldChargeStartingFareWhenDTravelDistanceLessThanBaseDistance() throws Exception {
        double distance = 2.9;
        Ride ride = new Ride(distance, 8);
        BigDecimal price = taxiCharger.chargeFee(ride);
        assertThat(price, is(BigDecimal.valueOf(11.0)));
    }

    @Test
    public void shouldChargeStartingFareWhenDTravelDistanceEqualsToBaseDistance() throws Exception {
        double distance = 3;
        Ride ride = new Ride(distance, 9);
        BigDecimal price = taxiCharger.chargeFee(ride);
        assertThat(price, is(BigDecimal.valueOf(11.0)));
    }

    @Test
    public void shouldChargeAddtionalFeeWhenTravelDistanceLargerThanBaseDistance() throws Exception {
        double distance = 5;
        Ride ride = new Ride(distance, 9);
        BigDecimal price = taxiCharger.chargeFee(ride);
        BigDecimal expectedValue = BigDecimal.valueOf(14.2);
        int result = price.compareTo(expectedValue);

        assertThat(result, is(0));
    }

    @Test
    public void shouldChargeHigherFeeWhenTravelDistanceHasDecimalNumber() throws Exception {
        double distance = 4.3;
        Ride ride = new Ride(distance, 10);
        BigDecimal price = taxiCharger.chargeFee(ride);
        assertThat(price, is(BigDecimal.valueOf(14.2)));
    }

    @Test
    public void shouldChargeNightStartingFeeWhenTravelDistanceLessThanBaseDistance() throws Exception {
        double distance = 2.9;
        Ride ride = new Ride(distance, 23);
        BigDecimal price = taxiCharger.chargeFee(ride);
        assertThat(price, is(BigDecimal.valueOf(13.0)));

    }

    @Test
    public void shouldChargeNightStartingFeeWhenTravelDistanceLargerThanBaseDistance() throws Exception {
        double distance = 3.5;
        Ride ride = new Ride(distance, 5);
        BigDecimal price = taxiCharger.chargeFee(ride);
        assertThat(price, is(BigDecimal.valueOf(15.4)));
    }

}
