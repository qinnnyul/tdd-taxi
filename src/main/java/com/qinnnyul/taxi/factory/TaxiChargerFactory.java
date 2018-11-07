package com.qinnnyul.taxi.factory;

import com.qinnnyul.taxi.charger.CompositeTaxiCharger;
import com.qinnnyul.taxi.charger.TaxiCharger;
import com.qinnnyul.taxi.config.TaxiConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class TaxiChargerFactory {
    private static final String DAY = "day";
    private static final String RANGE = "range";
    private static final String PRICE_PER_MILE = "pricePerMile";
    private static final String BASE_FEE = "baseFee";
    private static final String NIGHT = "night";
    private final TaxiConfig taxiConfig;

    public TaxiChargerFactory(TaxiConfig taxiConfig) {
        this.taxiConfig = taxiConfig;
    }

    public TaxiCharger getNightTaxiCharger(String taxiType) throws IOException {
        return build(taxiType, NIGHT);
    }

    public TaxiCharger getDayTaxiCharger(String taxiType) throws IOException {
        return build(taxiType, DAY);
    }

    private TaxiCharger build(String taxiType, String time) throws IOException {
        CompositeTaxiCharger taxiCharger = new CompositeTaxiCharger();

        String baseFee = taxiConfig.load(taxiType, time, BASE_FEE);
        taxiCharger.withBaseFee(new BigDecimal(baseFee));

        String rangeConfig = taxiConfig.load(taxiType, time, RANGE);

        List<Integer> ranges =
                Pattern.compile(",").splitAsStream(rangeConfig)
                        .map(Integer::valueOf).collect(toList());
        for (int i = 0; i < ranges.size(); i++){
            int minMeter = ranges.get(i);
            int maxMeter = ranges.size() - 1 == i ? Integer.MAX_VALUE : ranges.get(i + 1);
            String pricePerMile = taxiConfig.load(taxiType, time, String.format("%s.%s", PRICE_PER_MILE, minMeter));
            taxiCharger.withAdditionalFee(minMeter, maxMeter, new BigDecimal(pricePerMile));
        }

        return taxiCharger;
    }


}
