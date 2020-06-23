package case1.nl.data.repository;

import case1.nl.entities.Foodmoment;
import case1.nl.entities.Nevo201960;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class FoodMomentRepository extends DefaultRepository {

    public FoodMomentRepository(String pu) {
        super(pu);
    }

    public List<Foodmoment> getFoodMoments(int userID) {

        Query q = this.getEm().createQuery("select fm from Foodmoment fm "
                + "where fm.userid = :userID order by fm.moment desc")
                .setParameter("userID", userID);

        return q.getResultList();
    }

    public List<String> getProdListByDescEN(String query) {

        Query q = this.getEm().createQuery("select prod.proddescen "
                + "from Nevo201960 prod "
                + "where prod.proddescen like :query")
                .setParameter("query", "%" + query + "%");

        return q.getResultList();
    }

    public Nevo201960 getProdByDescEN(String prod) {

        Query q = this.getEm().createQuery("select prod "
                + "from Nevo201960 prod "
                + "where prod.proddescen = :prod")
                .setParameter("prod", prod);

        return (Nevo201960) q.getSingleResult();
    }

}
