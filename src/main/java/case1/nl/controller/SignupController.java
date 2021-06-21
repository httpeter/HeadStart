package case1.nl.controller;

import case1.nl.entities.User;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ManagedBean
@ViewScoped
public class SignupController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private User newUser = new User();



    public User getNewUser() {
        return newUser;

    }



//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return this.session;
    }



    public void setSession(SessionController session) {
        this.session = session;
    }



    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

//</editor-fold>


    @PostConstruct
    public void init() {
    }

}
