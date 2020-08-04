package case1.nl.data.repository;

import case1.nl.entities.Place;
import case1.nl.entities.Trip;
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

    public Trip getTrip(int id) {

        Query q = this.getEm().createQuery("select t from Trip t "
                + "where t.id = :id")
                .setParameter("id", id);

        return (Trip) q.getSingleResult();
    }

    public List<Place> getPlaces(int tripID) {
        Query q = this.getEm().createQuery("select p from Place p "
                + "where p.tripid = :tripID")
                .setParameter("tripID", tripID);
        return q.getResultList();
    }

}
