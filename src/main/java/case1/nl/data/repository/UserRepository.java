/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.data.repository;

import case1.nl.entities.User;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class UserRepository extends DefaultRepository {

    UserRepository() {
        super();
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
