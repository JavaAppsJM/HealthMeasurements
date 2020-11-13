package be.HV_Websites.healthmeasurements.repositories;

import be.HV_Websites.healthmeasurements.domain.BellyMeasurement;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BellyMRepository {

    public void addBellyM(BellyMeasurement bellyMesurement){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Entering add");
            System.out.println("New belly mesurement: " + bellyMesurement.toString());
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Domain creations
            System.out.println(bellyMesurement.toString());
            tx.begin();
            em.persist(bellyMesurement);
            tx.commit();
            System.out.println("add finished");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }

    public List<BellyMeasurement> getAllBellyMs() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<BellyMeasurement> bellyMesurements = new ArrayList<>();

        try {
            System.out.println("Entering getAll");
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx =em.getTransaction();
            TypedQuery<BellyMeasurement> query = em.createNamedQuery("getAllBellyMs", BellyMeasurement.class);

            tx.begin();
            bellyMesurements = query.getResultList();
            tx.commit();
            System.out.println("getAll finished");
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return bellyMesurements;
        }
    }

    public void deleteById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering delete");
            System.out.println("id: " + id);
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMeasurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMeasurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            BellyMeasurement bellyMesurement = queryEmp.getSingleResult();
            tx.commit();
            System.out.println("id found" + bellyMesurement.toString());
            // Remove employee
            tx.begin();
            em.remove(bellyMesurement);
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

    public BellyMeasurement findById(int id) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        BellyMeasurement bellyMesurement = new BellyMeasurement();

        try {
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMeasurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMeasurement.class);
            queryEmp.setParameter("srchid",id);
            tx.begin();
            bellyMesurement = queryEmp.getSingleResult();
            tx.commit();
        } catch (Exception exception){
            System.out.println(exception.getMessage().toString());
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
            return bellyMesurement;
        }
    }

    public void update(BellyMeasurement newBellyM) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            System.out.println("Entering update");
            System.out.println("New belly mesurement: " + newBellyM.toString());
            emf = Persistence.createEntityManagerFactory("mysqlcontainer");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            // Get employee by ID
            TypedQuery<BellyMeasurement> queryEmp = em.createNamedQuery("getBellyMById", BellyMeasurement.class);
            queryEmp.setParameter("srchid",newBellyM.getMesureId());
            tx.begin();
            BellyMeasurement oldBellyM = queryEmp.getSingleResult();
            tx.commit();
            System.out.println("Old belly mesurement: " + oldBellyM.toString());
            // Update employee in persistence area
            tx.begin();
            oldBellyM.setCircumRef(newBellyM.getCircumRef());
            oldBellyM.setMesureDate(newBellyM.getMesureDate());
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
