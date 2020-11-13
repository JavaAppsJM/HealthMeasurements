package be.HV_Websites.healthmeasurements.services;

public class HTMLIntDisplay extends HTMLInterface{
    private String[] hColNames;
    private String[][] hContent;


    public HTMLIntDisplay() {
    }

    public String[][] gethContent() {
        return hContent;
    }

    public void sethContent(String[][] hContent) {
        this.hContent = hContent;
    }

    public String[] gethColNames() {
        return hColNames;
    }

    public void sethColNames(String[] hColNames) {
        this.hColNames = hColNames;
    }

}
