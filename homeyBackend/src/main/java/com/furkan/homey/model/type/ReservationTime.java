package com.furkan.homey.model.type;

public enum ReservationTime {

    TIME1("09:00 - 12:00"), TIME2("12:00-15:00"), TIME3("15:00-18:00"),
    TIME4("18:00-21:00"), TIME5("21:00-24:00");

    private String display;

    private ReservationTime(String display) {this.display = display;}

    public String getDisplay() {return display;}

}
