package com.qinnnyul.taxi.proccessor;

import com.qinnnyul.taxi.charger.TaxiCharger;

public interface TaxiChargerProccessor {
    public boolean isApplicable(String taxiType);

    public TaxiCharger getDayTaxiCharger();

    public TaxiCharger getNightTaxiCharger();
}
