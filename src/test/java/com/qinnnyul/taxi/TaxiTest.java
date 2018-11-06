package com.qinnnyul.taxi;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TaxiTest {

    private Taxi taxi;

    @Before
    public void setUp() throws Exception {
        taxi = new Taxi();

    }

    @Test
    public void shouldChargeStartingFareWhenDTravelDistanceLessThanBaseDistance() throws Exception {
        double distance = 2.9;
        BigDecimal price = taxi.chargeFee(distance);
        assertThat(price, is(BigDecimal.valueOf(11)));
    }

    @Test
    public void shouldªChargeStartingFareWhenDTravelDistanceEqualsToBaseDistance() throws Exception {
        double distance = 3;
        BigDecimal price = taxi.chargeFee(distance);
        assertThat(price, is(BigDecimal.valueOf(11)));
    }

    @Test
    public void shouldChargeAddtionalFeeWhenTravelDistanceLargerThanBaseDistance() throws Exception {
        double distance = 5;
        BigDecimal price = taxi.chargeFee(distance);
        BigDecimal expectedValue = BigDecimal.valueOf(14.2);
        int result = price.compareTo(expectedValue);

        assertThat(result, is(0));
    }


}
