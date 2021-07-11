package com.project.chaletbooking.other;

public class Date_to_Reservation {
    String day;
    String month;
    String number_day;
    boolean check_click;
    int reservation_time;   // 1 morning
                            // 2 evening
                            // 3 morning +evening
                            // 0 not reservation
    int price;


//    used to upload data to firebase in Chalet    class Fragment Res_Booking_Fragment
    public Date_to_Reservation(String day, String month, String number_day, boolean check_click, int reservation_time, int price) {
        this.day = day;
        this.month = month;
        this.number_day = number_day;
        this.check_click = check_click;
        this.reservation_time = reservation_time;
        this.price = price;
    }

    public Date_to_Reservation(String day, String month, String number_day, boolean check_click, int reservation_time) {
        this.day = day;
        this.month = month;
        this.number_day = number_day;
        this.check_click = check_click;
        this.reservation_time = reservation_time;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(int reservation_time) {
        this.reservation_time = reservation_time;
    }

    public boolean isCheck_click() {
        return check_click;
    }

    public void setCheck_click(boolean check_click) {
        this.check_click = check_click;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNumber_day() {
        return number_day;
    }

    public void setNumber_day(String number_day) {
        this.number_day = number_day;
    }
}

