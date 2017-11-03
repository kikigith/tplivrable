package com.bootcamp.tp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bootcamp.tp.entities.Livrable;

public class LivrableRepository {

	private EntityManager em;
    private String persistUnit;
//    private Livrable livrable;
    private EntityManagerFactory emf;

    
 

    public LivrableRepository(String persistUnit) {
        
        this.persistUnit = persistUnit;
        emf=Persistence.createEntityManagerFactory(persistUnit);
        this.em=getEntityManager();
//        this.livrable=livrable;

    }
    
     public final EntityManager getEntityManager() {
        em= emf.createEntityManager();
        return em;
        
    }

  /**
   * methode genetique de creation dans la bdd
   * @param obj
   * @return
   * @throws SQLException 
   */
    public boolean create(Livrable obj) throws SQLException {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
       
        return true;
    }

    /**
     * M�thode pour supprimer une entite dans la bdd
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public boolean delete(Livrable obj) throws SQLException {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
        
        return true;
    }

    /**
     * M�thode de mise � jour
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public boolean update(Livrable obj) throws SQLException {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
       
        return true;
    }
    
    //a revoir
    public Livrable updateById(int id, Object value) throws SQLException {
        
//          String className = livrable.getSimpleName();
         
        String request = "select t from Livrable t where t." + id + "=:param";
        Query query = em.createQuery(request);
        query.setParameter("param", value);
        return (Livrable) query.getSingleResult();

    }

    /**
     * M�thode de recherche des informations
     *
     * @param propertyName
     * @param value
     * @return Livrable
     * @throws java.sql.SQLException
     */
    public List<Livrable> findByProperty(String propertyName, Object value) throws SQLException {
        String query = "select t from  Livrable t where t." + propertyName + "=:param";
        Query qry = getEntityManager().createQuery(query);
        qry.setParameter("param", value);
        return qry.getResultList();

    }
    /**
     * 
     * @param propertyName
     * @param value
     * @return
     * @throws SQLException 
     */
      public Livrable findByPropertyUnique(String propertyName, Object value) throws SQLException {
        String request = "select t from Livrable t where t." + propertyName + "=:param";
        Query query = getEm().createQuery(request);
        query.setParameter("param", value);
        return (Livrable) query.getSingleResult();

    }
    
     /*
	  * M�thode de recherche de tous les objets Livrable
     */
    public  List<Livrable> findAll() throws SQLException{
       String req="select t from Livrable t";
       Query query=getEm().createQuery(req);
        return query.getResultList();
    }
    

    /**
     * 
     * @return the entity manager
     */
    public EntityManager getEm() {
        return em;
    }


    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * 
     * @param em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * 
     * @return the persistence unit
     */
    public String getPersistUnit() {
        return persistUnit;
    }

    /**
     * 
     * @param persistUnit 
     */
    public void setPersistUnit(String persistUnit) {
        this.persistUnit = persistUnit;
    }

}
