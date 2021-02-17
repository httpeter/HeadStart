// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package case1.nl.data.repository;

import case1.nl.util.FMessage;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.EntityType;

/**
 * Simple extendable repository for use with JPA2 offering basic list retrieval
 * en persistence functionalities. Requires the name of the used Persistence
 * Unit.
 *
 */
public class DefaultRepository implements Serializable {

    private static final Logger logger = Logger.getLogger(DefaultRepository.class
            .getName());
    private static final long serialVersionUID = 7086626098229281352L;

    public EntityManagerFactory emf;

    private EntityManager em;



    public EntityManager getEm() {
        return em;
    }



    public void setEm(EntityManager em) {
        this.em = em;
    }



    public DefaultRepository(String puName) {

        emf = Persistence.createEntityManagerFactory(puName);
        em = emf.createEntityManager();

    }



    public boolean persisted(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.clear();
            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getCause().getMessage());
            try {
                em.getTransaction().rollback();
            } catch (Exception e1) {
                logger.log(Level.WARNING, e1.getCause().getMessage());
            }
            return false;
        }
    }



    public boolean merged(Object object) {
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
            em.clear();
            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getCause().getMessage());
            try {
                em.getTransaction().rollback();
            } catch (Exception e1) {
                logger.log(Level.WARNING, e1.getCause().getMessage());
            }
            return false;
        }
    }



    public List findAll(Class c) {

        Query query = null;
        try {
            query = em.createNamedQuery(c.getSimpleName() + ".findAll");
        } catch (Exception e) {
            FMessage.error(e.getLocalizedMessage());
        }

        return query.getResultList();
    }



    public List findByTypedQueryName(String namedQueryName, Object parameterValue) {

        Query query = null;

        String parameter = namedQueryName
                .replace(namedQueryName.substring(0, namedQueryName.indexOf(".findBy") + 7), "")
                .toLowerCase();

        try {
            query = em.createNamedQuery(namedQueryName);
            query.setParameter(parameter, parameterValue);

        } catch (Exception e) {
            FMessage.error(e.getLocalizedMessage());
        }

        return query.getResultList();
    }



    public boolean deleted(Object object) {

        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            em.clear();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            logger.log(Level.WARNING, e.getCause().getMessage());
            return false;
        }

    }



    public void close() {
        if (em.isOpen()) {
            em.clear();
            em.close();
            emf.close();
        }
    }



    public Set<NamedQuery> findAllNamedQueries() {
        Set<NamedQuery> allNamedQueries = new HashSet<NamedQuery>();

        Set<EntityType<?>> entityTypes = emf.getMetamodel().getEntities();
        for (EntityType<?> entityType : entityTypes) {
            Class<?> entityClass = entityType.getBindableJavaType();

            NamedQueries namedQueries = entityClass.getAnnotation(NamedQueries.class);
            if (namedQueries != null) {
                allNamedQueries.addAll(Arrays.asList(namedQueries.value()));
            }

            NamedQuery namedQuery = entityClass.getAnnotation(NamedQuery.class);
            if (namedQuery != null) {
                allNamedQueries.add(namedQuery);
            }
        }
        return allNamedQueries;
    }

}
