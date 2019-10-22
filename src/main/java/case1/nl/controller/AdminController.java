package case1.nl.controller;

import case1.nl.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class AdminController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List<User> users;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
//</editor-fold>

    @PostConstruct
    private void init() {
        users = session.getUserRepository().getResultList(User.class);
    }

}
