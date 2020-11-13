package be.HV_Websites.healthmeasurements.services;

public abstract class HTMLInterface {
    private String hTitle;
    private String hFormActionUrl;
    private String hDataGroupName; //hMType

    public HTMLInterface() {
    }

    public String gethDataGroupName() {
        return hDataGroupName;
    }

    public void sethDataGroupName(String hDataGroupName) {
        this.hDataGroupName = hDataGroupName;
    }

    public String gethTitle() {
        return hTitle;
    }

    public void sethTitle(String hTitle) {
        this.hTitle = hTitle;
    }

    public String gethFormActionUrl() {
        return hFormActionUrl;
    }

    public void sethFormActionUrl(String hFormActionUrl) {
        this.hFormActionUrl = hFormActionUrl;
    }

}
