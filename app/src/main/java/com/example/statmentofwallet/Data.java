package com.example.statmentofwallet;

public class Data {
    int id;
    String money;
    String day;
    String time;
    String reason;
    public Data(int ID, String Monay, String Reason, String Day, String Time){
        id = ID;
        money = Monay;
        reason = Reason;
        day = Day;
        time = Time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String data) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
