package be.HV_Websites.healthmeasurements.repositories;

import be.HV_Websites.healthmeasurements.domain.BloodPressureMeasurement;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BPresMRepository {

    public void addBPresM(BloodPressureMeasurement measurement){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Entering add");
            System.out.println("New measurement: " + measurement.toString());
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Domain creations
            tx.begin();
            em.persist(measurement);
            tx.commit();
            System.out.println("add finished");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public List<BloodPressureMeasurement> getAllBPresMs() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<BloodPressureMeasurement> measurement = new ArrayList<>();

        try {
            System.out.println("Entering getAll");
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx =em.getTransaction();
            TypedQuery<BloodPressureMeasurement> query = em.createNamedQuery("getAllBloodMs", BloodPressureMeasurement.class);

            tx.begin();
            measurement = query.getResultList();
            tx.commit();
            System.out.println("getAll finished");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return measurement;
        }
    }

    public BloodPressureMeasurement findBPresById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        BloodPressureMeasurement measurement = new BloodPressureMeasurement();

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BloodPressureMeasurement> queryEmp = em.createNamedQuery("getBloodMById", BloodPressureMeasurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            measurement = queryEmp.getSingleResult();
            tx.commit();
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return measurement;
        }
    }

    public void deleteBPresById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering delete");
            System.out.println("id: " + id);
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get by ID
            TypedQuery<BloodPressureMeasurement> queryEmp = em.createNamedQuery("getBloodMById", BloodPressureMeasurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            BloodPressureMeasurement measurement = queryEmp.getSingleResult();
            tx.commit();
            System.out.println("id found" + measurement.toString());
            // Remove employee
            tx.begin();
            em.remove(measurement);
            tx.commit();
            System.out.println("delete finished");
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Delete is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public void updateBPres(BloodPressureMeasurement newMeasurement) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering update");
            System.out.println("New measurement: " + newMeasurement.toString());
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get by ID
            TypedQuery<BloodPressureMeasurement> queryEmp = em.createNamedQuery("getBloodMById", BloodPressureMeasurement.class);
            queryEmp.setParameter("srchid",newMeasurement.getMesureId());
            tx.begin();
            BloodPressureMeasurement oldBellyM = queryEmp.getSingleResult();
            tx.commit();
            System.out.println("Old belly mesurement: " + oldBellyM.toString());
            // Update employee in persistence area
            tx.begin();
            oldBellyM.setHeartBeat(newMeasurement.getHeartBeat());
            oldBellyM.setBloodPressureHigh(newMeasurement.getBloodPressureHigh());
            oldBellyM.setBloodPressureLow(newMeasurement.getBloodPressureLow());
            oldBellyM.setMesureDate(newMeasurement.getMesureDate());
            tx.commit();
            System.out.println("Update executed");
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
            System.out.println("Update is not executed");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

}
