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



    public List<Trip> getTripsByDateAsc(int curUserId, Date filterDate) {

        Query q = this.getEm().createQuery("SELECT t FROM Trip t "
                + "WHERE t.owninguserid = :curUserId "
                + "AND t.startdate > :filterDate "
                + "ORDER BY t.startdate ASC")
                .setParameter("filterDate", filterDate)
                .setParameter("curUserId", curUserId);

        return q.getResultList();
    }

}
