package be.HV_Websites.healthmeasurements.domain;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(name = "getAllBellyMs",query = "select m from BellyMesurement as m order by m.mesureDate")
@NamedQuery(name = "getBellyMById",query = "select m from BellyMesurement as m where m.mesureId=:srchid")
@Entity
public class BellyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mesureId;
    private LocalDate mesureDate;
    private double circumRef;

    public BellyMeasurement() {
    }

    public BellyMeasurement(double circumRef) {
        this.circumRef = circumRef;
        this.mesureDate = LocalDate.now();
    }

    public BellyMeasurement(LocalDate mesureDate, double circumRef) {
        this.mesureDate = mesureDate;
        this.circumRef = circumRef;
    }

    // Toch voorzien om de id bij een edit postmapping te kunnen binnen krijgen !
    public void setMesureId(int mesureId) {
        this.mesureId = mesureId;
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

    public double getCircumRef() {
        return circumRef;
    }

    public void setCircumRef(double circumRef) {
        this.circumRef = circumRef;
    }

    @Override
    public String toString() {
        return "BellyMeasurement{" +
                "mesureId=" + mesureId +
                ", mesureDate=" + mesureDate +
                ", circumRef=" + circumRef +
                '}';
    }
}
