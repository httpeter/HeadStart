package case1.nl.data.repository;

import case1.nl.entities.User;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class UserRepository extends DefaultRepository {

    public UserRepository(String pu) {
        super(pu);
    }

    public User getUser(User user) {
        Query q = this.getEm().createQuery("select u from User u "
                + "where u.email = :email").setParameter("email", user.getEmail());
        return (User) q.getSingleResult();
    }

}
