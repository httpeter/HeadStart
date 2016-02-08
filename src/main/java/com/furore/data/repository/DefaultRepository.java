// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package com.furore.data.repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DefaultRepository implements Serializable
{

    private static final Logger logger = Logger.getLogger(DefaultRepository.class
            .getName());

    private EntityManagerFactory emf;

    private EntityManager em;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public EntityManagerFactory getEmf()
    {
        return emf;
    }



    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }



    public EntityManager getEm()
    {
        return em;
    }



    public void setEm(EntityManager em)
    {
        this.em = em;
    }
//</editor-fold>



    /**
     * Simple extendable repository for use with JPA2 offering basic list
     * retrieval en persistence functionalities. Requires the name of the used
     * Persistence Unit.
     *
     * @param persistenceUnitName
     */
    public DefaultRepository(String persistenceUnitName)
    {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }



    /**
     * Checking whether Entity Manager and Entity Manager Factory are open. made
     * public so that it can be used by classes that extend DefaultRepository.
     *
     * @return
     *
     */
    public boolean emIsOpen()
    {
        if (!emf.isOpen())
        {
            logger.warning("EMF is closed!");
        }
        if (!em.isOpen())
        {
            logger.warning("EM is closed!");
        }
        return emf.isOpen() && em.isOpen();
    }



    /**
     * Saving an object to the database. If EntityManager or
     * EntityManagerFactory are closed an error is thrown. The latter can be
     * checked with 'emIsOpen'.
     *
     * @param object
     * @return
     */
    public boolean persisted(Object object)
    {
        if (emIsOpen())
        {
            try
            {
                em.getTransaction().begin();
                em.persist(object);
                em.getTransaction().commit();
                em.clear();
                return true;
            } catch (Exception e)
            {
                logger.log(Level.WARNING, e.getCause().getMessage());
                try
                {
                    em.getTransaction().rollback();
                } catch (Exception e1)
                {
                    logger.log(Level.WARNING, e1.getCause().getMessage());
                }
                return false;
            }
        } else
        {
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
    public List getResultList(Class c)
    {
        if (emIsOpen())
        {
            TypedQuery q = em.createQuery("select o from "
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
    public boolean deleted(Object object)
    {
        if (emIsOpen())
        {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction()
                    .commit();
            em.clear();
            return true;
        } else
        {
            System.out.println("EntityManagerFactory or EntityManager are closed");
            em.getTransaction()
                    .rollback();
            return false;
        }

    }



    /**
     * Method for closing the EntityManager and the EntityManagerFactory. If
     * closed, other methods persistence breaks.
     */
    public void close()
    {
        if (emIsOpen())
        {
            em.clear();
            em.close();
            emf.close();
        }
    }

}
