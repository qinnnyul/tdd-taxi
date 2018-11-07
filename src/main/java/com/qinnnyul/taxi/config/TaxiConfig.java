package com.qinnnyul.taxi.config;

import java.io.IOException;
import java.util.Properties;

public class TaxiConfig {

    private String resourceName;

    public TaxiConfig(String resourceName) {
        this.resourceName = resourceName;
    }

    public String load(String taxiType, String time, String key) throws IOException {
        Properties properties = new Properties();

        properties.load(this.getClass().getResourceAsStream(resourceName));

        return properties.getProperty(String.format("%s.%s.%s", taxiType, time, key));
    }
}
