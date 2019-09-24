/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.data.repository;

import case1.nl.entities.User;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class UserRepository extends DefaultRepository {

    private static UserRepository instance = null;

    public static UserRepository getInstance(String puName) {
        if (instance == null) {
            try {
                instance = new UserRepository();
                instance.setEmf(Persistence.createEntityManagerFactory(puName));
                instance.setEm(emf.createEntityManager());
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public User getUser(String email) {
        if (instance.emIsOpen()) {
            Query q = this.getEm().createQuery("select u from User u "
                    + "where u.email = :email").setParameter("email", email);
            return (User) q.getSingleResult();
        }
        return null;
    }

}
