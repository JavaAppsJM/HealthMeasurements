package be.HV_Websites.healthmeasurements.services;

import be.HV_Websites.healthmeasurements.domain.BellyMeasurement;
import be.HV_Websites.healthmeasurements.domain.BloodPressureMeasurement;
import be.HV_Websites.healthmeasurements.repositories.BPresMRepository;
import be.HV_Websites.healthmeasurements.repositories.BellyMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MeasurementService {
    private MessageSource ms;
    private Locale locale;

    @Autowired
    BellyMRepository bellyMRepo;
    @Autowired
    BPresMRepository bpresMRepo;

    @Autowired
    public void setMs(MessageSource ms) {
        this.ms = ms;
    }

    @Value("#{T(java.util.Locale).getDefault()}")
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    // Fill Nav Bar
    public List<NavBarLine> fillNavBar(){
        // Prepare Nav Bar
        List<NavBarLine> navBar = new ArrayList<>();
        NavBarLine navLine1 = new NavBarLine();
        navLine1.setNavText(ms.getMessage("start",new Object[] {}, locale));
        navLine1.setNavURL("/healthmeasurements/start");
        navBar.add(navLine1);
        NavBarLine navLine2 = new NavBarLine();
        navLine2.setNavText(ms.getMessage("showBelly",new Object[] {}, locale));
        navLine2.setNavURL("/healthmeasurements/getAllGenBellyMs");
        navBar.add(navLine2);
        NavBarLine navLine3 = new NavBarLine();
        navLine3.setNavText(ms.getMessage("showBPres",new Object[] {}, locale));
        navLine3.setNavURL("/healthmeasurements/getAllGenBPresMs");
        navBar.add(navLine3);
        NavBarLine navLine4 = new NavBarLine();
        navLine4.setNavText(ms.getMessage("addBelly",new Object[] {}, locale));
        navLine4.setNavURL("/healthmeasurements/addGenBellyM");
        navBar.add(navLine4);
        NavBarLine navLine5 = new NavBarLine();
        navLine5.setNavText(ms.getMessage("addBPres",new Object[] {}, locale));
        navLine5.setNavURL("/healthmeasurements/addGenBPresM");
        navBar.add(navLine5);

        return navBar;
    }

    public HTMLIntStart fillIntStart(){
        HTMLIntStart hIntStart = new HTMLIntStart();
        hIntStart.sethTitle(ms.getMessage("startTitle",new Object[] {}, locale));
        hIntStart.setNavBar(fillNavBar());

        return hIntStart;
    }

    // Belly services
    public HTMLIntDisplay fillIntDisplayWBelly(List<BellyMeasurement> bellyMesurementList) {
        HTMLIntDisplay hColTemplate = new HTMLIntDisplay();
        hColTemplate.sethTitle(ms.getMessage("bellydisplayTitle",new Object[] {}, locale));
        hColTemplate.sethDataGroupName("BellyM");
        hColTemplate.setNavBar(fillNavBar());

        // Prepare col names
        String[] colList = new String[4];
        colList[0] = ms.getMessage("measurementId",new Object[] {}, locale);
        colList[1] = ms.getMessage("measurementDate",new Object[] {}, locale);
        colList[2] = ms.getMessage("circumreference", new Object[] {}, locale);
        colList[3] = ms.getMessage("actions",new Object[] {}, locale);
        hColTemplate.sethColNames(colList);

        // Prepare Content
        String[][] displayList = new String[bellyMesurementList.size()][3];
        int i = 0;
        int j = 0;
        for (BellyMeasurement b: bellyMesurementList) {
            displayList[i][j] = String.valueOf(b.getMesureId());
            j++;
            displayList[i][j] = String.valueOf(b.getMesureDate());
            j++;
            displayList[i][j] = String.valueOf(b.getCircumRef());
            j = 0;
            i++;
        }
        hColTemplate.sethContent(displayList);

        return hColTemplate;
    }

    public HTMLIntEdit fillIntEditWBelly(BellyMeasurement bellyMesurement, String action){
        // Prepare formfields
        String[][] tempList = new String[1][3];
        tempList[0][0] = ms.getMessage("circumreference", new Object[] {}, locale);
        tempList[0][1] = "circumRef";
        tempList[0][2] = String.valueOf(bellyMesurement.getCircumRef());

        // Fill template
        HTMLIntEdit htmlTemplate = new HTMLIntEdit();
        htmlTemplate.sethTitle(ms.getMessage(action + "bellyTitle", new Object[] {}, locale));
        String formActURL = "/healthmeasurements/" + action + "GenBellyM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.sethFormContentList(tempList);
        htmlTemplate.setCircumRef(bellyMesurement.getCircumRef());
        htmlTemplate.setMesureId(bellyMesurement.getMesureId());
        if (bellyMesurement.getMesureDate() == null){
            bellyMesurement.setMesureDate(LocalDate.now());
        }
        htmlTemplate.setDay(bellyMesurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(bellyMesurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(bellyMesurement.getMesureDate().getYear());
        htmlTemplate.setLabelMesureDate(ms.getMessage("labelDate", new Object[] {}, locale));
        htmlTemplate.setNavBar(fillNavBar());

        return htmlTemplate;
    }

    public BellyMeasurement moveIntEditInBelly(HTMLIntEdit template){
        BellyMeasurement bellyMesurement = new BellyMeasurement();
        bellyMesurement.setMesureId(template.getMesureId());
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            bellyMesurement.setMesureDate(LocalDate.now());
        } else {
            bellyMesurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        bellyMesurement.setCircumRef(template.getCircumRef());

        return bellyMesurement;
    }

    // Belly reposervices
    public List<BellyMeasurement> getAllBellyMs(){
        return bellyMRepo.getAllBellyMs();
    }

    public void addBellyM(BellyMeasurement bellyMesurement) {
        bellyMRepo.addBellyM(bellyMesurement);
    }

    public BellyMeasurement findById(int id) {
        return bellyMRepo.findById(id);
    }

    public void update(BellyMeasurement bellyMesurement) {
        bellyMRepo.update(bellyMesurement);
    }

    public void deleteById(int id) {
        bellyMRepo.deleteById(id);
    }


    // Blood pressure services
    public HTMLIntDisplay fillIntDisplayWBPres(List<BloodPressureMeasurement> bloodPressureMesurementList) {
        HTMLIntDisplay hColTemplate = new HTMLIntDisplay();
        hColTemplate.sethTitle(ms.getMessage("bpresdisplayTitle",new Object[] {}, locale));
        hColTemplate.sethDataGroupName("BPresM");
        hColTemplate.setNavBar(fillNavBar());

        // Prepare col names
        String[] colList = new String[6];
        colList[0] = ms.getMessage("measurementId",new Object[] {}, locale);
        colList[1] = ms.getMessage("measurementDate",new Object[] {}, locale);
        colList[2] = ms.getMessage("heartbeat", new Object[] {}, locale);
        colList[3] = ms.getMessage("bloodpressureh", new Object[] {}, locale);
        colList[4] = ms.getMessage("bloodpressurel", new Object[] {}, locale);
        colList[5] = ms.getMessage("actions",new Object[] {}, locale);
        hColTemplate.sethColNames(colList);

        // Prepare Content
        String[][] displayList = new String[bloodPressureMesurementList.size()][5];
        int i = 0;
        int j = 0;
        for (BloodPressureMeasurement b: bloodPressureMesurementList) {
            displayList[i][j] = String.valueOf(b.getMesureId());
            j++;
            displayList[i][j] = String.valueOf(b.getMesureDate());
            j++;
            displayList[i][j] = String.valueOf(b.getHeartBeat());
            j++;
            displayList[i][j] = String.valueOf(b.getBloodPressureHigh());
            j++;
            displayList[i][j] = String.valueOf(b.getBloodPressureLow());
            j = 0;
            i++;
        }
        hColTemplate.sethContent(displayList);

        return hColTemplate;
    }

    public HTMLIntEdit fillIntEditWBPres(BloodPressureMeasurement measurement, String action){
        HTMLIntEdit htmlTemplate = new HTMLIntEdit();
        htmlTemplate.sethTitle(ms.getMessage(action + "bpresTitle", new Object[] {}, locale));
        String formActURL = "/healthmeasurements/" + action + "GenBPresM";
        htmlTemplate.sethFormActionUrl(formActURL);
        htmlTemplate.setNavBar(fillNavBar());

        // Prepare formfields
        String[][] tempList = new String[3][3];
        tempList[0][0] = ms.getMessage("heartbeat", new Object[] {}, locale);
        tempList[0][1] = "HeartBeat";
        tempList[1][0] = ms.getMessage("bloodpressureh", new Object[] {}, locale);
        tempList[1][1] = "bloodPressureHigh";
        tempList[2][0] = ms.getMessage("bloodpressurel", new Object[] {}, locale);
        tempList[2][1] = "bloodPressureLow";
        tempList[0][2] = String.valueOf(measurement.getHeartBeat());
        tempList[1][2] = String.valueOf(measurement.getBloodPressureHigh());
        tempList[2][2] = String.valueOf(measurement.getBloodPressureLow());
        htmlTemplate.sethFormContentList(tempList);

        htmlTemplate.setCircumRef(measurement.getHeartBeat());
        htmlTemplate.setCircumRef(measurement.getBloodPressureHigh());
        htmlTemplate.setCircumRef(measurement.getBloodPressureLow());
        htmlTemplate.setMesureId(measurement.getMesureId());
        if (measurement.getMesureDate() == null){
            measurement.setMesureDate(LocalDate.now());
        }
        htmlTemplate.setDay(measurement.getMesureDate().getDayOfMonth());
        htmlTemplate.setMonth(measurement.getMesureDate().getMonthValue());
        htmlTemplate.setYear(measurement.getMesureDate().getYear());
        htmlTemplate.setLabelMesureDate(ms.getMessage("labelDate", new Object[] {}, locale));

        return htmlTemplate;
    }

    public BloodPressureMeasurement moveIntEditInBPres(HTMLIntEdit template){
        BloodPressureMeasurement measurement = new BloodPressureMeasurement();
        measurement.setMesureId(template.getMesureId());
        if (template.getDay() == 0  || template.getMonth() == 0 || template.getYear() == 0){
            measurement.setMesureDate(LocalDate.now());
        } else {
            measurement.setMesureDate
                    (LocalDate.of(template.getYear(),template.getMonth(),template.getDay()));
        }
        measurement.setHeartBeat(template.getHeartBeat());
        measurement.setBloodPressureHigh(template.getBloodPressureHigh());
        measurement.setBloodPressureLow(template.getBloodPressureLow());

        return measurement;
    }

    // Blood Pressure repo services
    public List<BloodPressureMeasurement> getAllBPresMs(){
        return bpresMRepo.getAllBPresMs();
    }

    public void addBPresM(BloodPressureMeasurement measurement) {
        bpresMRepo.addBPresM(measurement);
    }

    public BloodPressureMeasurement findBPresById(int id) {
        return bpresMRepo.findBPresById(id);
    }

    public void updateBPres(BloodPressureMeasurement measurement) {
        bpresMRepo.updateBPres(measurement);
    }

    public void deleteBPresById(int id) {
        bpresMRepo.deleteBPresById(id);
    }
}
