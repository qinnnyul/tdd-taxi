package com.qinnnyul.taxi.model;

public class Ride {
    private final double distance;
    private final int hourOfDay;

    public Ride(double distance, int hourOfDay) {
        this.distance = distance;
        this.hourOfDay = hourOfDay;
    }

    public double getDistance() {
        return distance;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }
}
