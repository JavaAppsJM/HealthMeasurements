package be.HV_Websites.healthmeasurements.services;

import java.util.List;

public abstract class HTMLInterface {
    private String hTitle;
    private String hFormActionUrl;
    private String hDataGroupName;
    private List<NavBarLine> navBar;

    public HTMLInterface() {
    }

    public List<NavBarLine> getNavBar() {
        return navBar;
    }

    public void setNavBar(List<NavBarLine> navBar) {
        this.navBar = navBar;
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
