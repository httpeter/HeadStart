package case1.nl.data.repository;

import case1.nl.entities.Foodmoment;
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
                + "where fm.userid = :userID")
                .setParameter("userID", userID);
        
        return q.getResultList();
    }

}
