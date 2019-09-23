// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package case1.nl.data.repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Simple extendable repository for use with JPA2 offering basic list retrieval
 * en persistence functionalities. Requires the name of the used Persistence
 * Unit.
 *
 */
public class DefaultRepository implements Serializable {

    public static DefaultRepository instance = null;

    private static final Logger logger = Logger.getLogger(DefaultRepository.class
            .getName());
    private static final long serialVersionUID = 7086626098229281352L;

    private static EntityManagerFactory emf;

    private EntityManager em;

    public static DefaultRepository getInstance(String puName) {
        if (instance == null) {
            try {
                instance = new DefaultRepository();
                instance.setEmf(Persistence.createEntityManagerFactory(puName));
                instance.setEm(emf.createEntityManager());
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        return instance;
    }

//<editor-fold defaultstate="collapsed" desc="'stupid'Getters & Setters">
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
//</editor-fold>

    /**
     * Checking whether Entity Manager and Entity Manager Factory are open. made
     * public so that it can be used by classes that extend DefaultRepository.
     *
     * @return
     *
     */
    public boolean emIsOpen() {
        if (!instance.emf.isOpen()) {
            logger.warning("EMF is closed!");
        }
        if (!instance.em.isOpen()) {
            logger.warning("EM is closed!");
        }
        return instance.emf.isOpen() && instance.em.isOpen();
    }

    /**
     * Saving an object to the database. If EntityManager or
     * EntityManagerFactory are closed an error is thrown. The latter can be
     * checked with 'emIsOpen'.
     *
     * @param object
     * @return
     */
    public boolean persisted(Object object) {
        if (instance.emIsOpen()) {
            try {
                instance.em.getTransaction().begin();
                instance.em.persist(object);
                instance.em.getTransaction().commit();
                instance.em.clear();
                return true;
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getCause().getMessage());
                try {
                    instance.em.getTransaction().rollback();
                } catch (Exception e1) {
                    logger.log(Level.WARNING, e1.getCause().getMessage());
                }
                return false;
            }
        } else {
            System.out.println("EntityManagerFactor or EntityManager are closed");
            return false;
        }
    }

    /**
     * Retrieve a list of objects according to their type from the DB.
     *
     * @param c
     * @return
     */
    public List getResultList(Class c) {
        if (instance.emIsOpen()) {
            TypedQuery q = instance.em.createQuery("select o from "
                    + c.getSimpleName()
                    + " o", c);
            return q.getResultList();
        }
        return null;
    }

    /**
     * Delete a specific object from the DB. Does not work with objects
     * extending Collection! If the EntityManager and the EntityManagerFactory
     * are closed, an error is thrown
     *
     * @param object
     * @return
     */
    public boolean deleted(Object object) {
        if (instance.emIsOpen()) {
            instance.em.getTransaction().begin();
            instance.em.remove(object);
            instance.em.getTransaction()
                    .commit();
            instance.em.clear();
            return true;
        } else {
            System.out.println("EntityManagerFactory or EntityManager are closed");
            instance.em.getTransaction()
                    .rollback();
            return false;
        }

    }

    /**
     * Method for closing the EntityManager and the EntityManagerFactory. If
     * closed, other methods persistence breaks.
     */
    public void close() {
        if (instance.emIsOpen()) {
            instance.em.clear();
            instance.em.close();
            instance.emf.close();
        }
    }

}
