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

    public Trip getTrip(String name) {

        Query q = this.getEm().createQuery("select t from Trip t "
                + "where t.name = :name")
                .setParameter("name", name);

        return (Trip) q.getSingleResult();
    }

}
