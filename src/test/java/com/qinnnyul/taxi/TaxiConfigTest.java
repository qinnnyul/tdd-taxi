package com.qinnnyul.taxi;

import com.qinnnyul.taxi.config.TaxiConfig;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxiConfigTest {

    private TaxiConfig taxiConfig;

    @Before
    public void setUp() throws Exception {
        this.taxiConfig = new TaxiConfig("/taxi.properties");
    }

    @Test
    public void shouldLoadConfigDataFromResource() throws Exception {
        String result = taxiConfig.load("normal", "day", "baseFee");
        assertThat(result, is("11"));
    }

    @Test
    public void shouldLoadItemWhichContainsSpecialChar() throws Exception {
        String result = taxiConfig.load("shangHaiInner", "night", "range");
        assertThat(result, is("3,10"));
    }
}
