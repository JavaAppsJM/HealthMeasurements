package be.HV_Websites.healthmeasurements.services;

import java.time.LocalDate;

public class HTMLIntEdit extends HTMLInterface{
    private String[][] hFormContentList;
    private int mesureId;
    private LocalDate mesureDate;
    private String labelMesureDate;
    private int day;
    private int month;
    private int year;
    private double circumRef;
    private double bloodPressureHigh;
    private double bloodPressureLow;
    private double heartBeat;

    public HTMLIntEdit() {
    }

    public String getLabelMesureDate() {
        return labelMesureDate;
    }

    public void setLabelMesureDate(String labelMesureDate) {
        this.labelMesureDate = labelMesureDate;
    }

    public String[][] gethFormContentList() {
        return hFormContentList;
    }

    public void sethFormContentList(String[][] hFormContentList) {
        this.hFormContentList = hFormContentList;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCircumRef() {
        return circumRef;
    }

    public void setMesureId(int mesureId) {
        this.mesureId = mesureId;
    }

    public void setCircumRef(double circumRef) {
        this.circumRef = circumRef;
    }

    public int getMesureId() {
        return mesureId;
    }

    public LocalDate getMesureDate() {
        return mesureDate;
    }

    public void setMesureDate(LocalDate mesureDate) {
        this.mesureDate = mesureDate;
    }

    public double getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(double bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public double getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(double bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public double getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(double heartBeat) {
        this.heartBeat = heartBeat;
    }

}
