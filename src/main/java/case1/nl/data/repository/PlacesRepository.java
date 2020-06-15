package case1.nl.data.repository;

import case1.nl.entities.Trip;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class PlacesRepository extends DefaultRepository {

    public PlacesRepository(String pu) {
        super(pu);
    }

    public Trip getTrip(int id) {

        Query q = this.getEm().createQuery("select t from Trip t "
                + "where t.id = :id")
                .setParameter("id", id);

        return (Trip) q.getSingleResult();
    }

}
