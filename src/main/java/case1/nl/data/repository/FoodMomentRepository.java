package case1.nl.data.repository;

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

    
    public List<String> getProdListByDescEN(String query) {

        Query q = this.getEm().createQuery("select prod.proddescen "
                + "from Nevo201960 prod "
                + "where prod.proddescen like :query")
                .setParameter("query", "%" + query + "%");

        return q.getResultList();
    }



}
