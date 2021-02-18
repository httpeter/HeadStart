package case1.nl.data.repository;

import case1.nl.entities.SysPage;
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



    public User getUser(String email, String pwdHash) {
        Query q = this.getEm().createQuery("select u from User u "
                + "where u.email = :email and u.pwdhash = :pwdHash")
                .setParameter("email", email)
                .setParameter("pwdHash", pwdHash);
        User u = null;
        try {
            u = (User) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("--> No entities retrieved when querying for user "
                    + email);
        }
        return u;
    }

}
