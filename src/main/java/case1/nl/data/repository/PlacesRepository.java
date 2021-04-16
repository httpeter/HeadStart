package case1.nl.data.repository;

import case1.nl.entities.Trip;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class PlacesRepository extends DefaultRepository {

    public PlacesRepository(String pu) {
        super(pu);
    }



    public List<Trip> getTripsByDateAsc(int curUserId, int curYear) {

        Query q = this.getEm().createQuery("SELECT t FROM Trip t "
                + "WHERE t.owninguserid = :curUserId "
                + "AND t.startdate LIKE :curYear "
                + "AND t.startdate > :curDate "
                + "ORDER BY t.startdate ASC")
                .setParameter("curYear", curYear + "%")
                .setParameter("curUserId", curUserId)
                .setParameter("curDate", new Date());

        return q.getResultList();
    }

}
