package be.HV_Websites.healthmeasurements.services;

public class NavBarLine {
    private String navTitle;
    private String navId;
    private String navClass;
    private String navText;
    private String navURL;

    public NavBarLine() {
    }

    public String getNavId() {
        return navId;
    }

    public void setNavId(String navId) {
        this.navId = navId;
    }

    public String getNavClass() {
        return navClass;
    }

    public void setNavClass(String navClass) {
        this.navClass = navClass;
    }

    public String getNavTitle() {
        return navTitle;
    }

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }

    public String getNavText() {
        return navText;
    }

    public void setNavText(String navText) {
        this.navText = navText;
    }

    public String getNavURL() {
        return navURL;
    }

    public void setNavURL(String navURL) {
        this.navURL = navURL;
    }
}
