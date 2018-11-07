package com.qinnnyul.taxi;

import com.qinnnyul.taxi.charger.TaxiCharger;
import com.qinnnyul.taxi.charger.TimeBasedTaxiCharger;
import com.qinnnyul.taxi.proccessor.NormalTaxiProccessor;
import com.qinnnyul.taxi.proccessor.ShanghaiInnerTaxiProccessor;
import com.qinnnyul.taxi.proccessor.ShanghaiOuterTaxiProccessor;
import com.qinnnyul.taxi.proccessor.TaxiChargerProccessor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaxiChargeFactory {

    public static final double BASE_DISTANCE = 3;

    public TaxiCharger getTaxiCharger(String taxiType) {

        List<TaxiChargerProccessor> taxiChargerProccessors = Arrays.asList(new NormalTaxiProccessor(), new ShanghaiInnerTaxiProccessor(), new ShanghaiOuterTaxiProccessor());

        Optional<TaxiChargerProccessor> chargerProccessor = taxiChargerProccessors.stream()
                .filter(taxiChargerProccessor -> taxiChargerProccessor.isApplicable(taxiType))
                .findFirst();

        return new TimeBasedTaxiCharger(chargerProccessor.get().getDayTaxiCharger(), chargerProccessor.get().getNightTaxiCharger());

    }

}



