package case1.nl.data.repository;

import case1.nl.entities.User;
import case1.nl.util.AESEncryptor;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class UserRepository extends DefaultRepository {
    
    public UserRepository(String pu) {
        super(pu);
    }
    
    public User getUser(String email, String pwd) throws Exception {
        AESEncryptor cryptor = new AESEncryptor();
        Query q = this.getEm().createQuery("select u from User u "
                + "where u.email = :email and u.pwdhash = :pwd")
                .setParameter("email", email)
                .setParameter("pwd", cryptor.encrypt(pwd));
        return (User) q.getSingleResult();
    }
    
}
