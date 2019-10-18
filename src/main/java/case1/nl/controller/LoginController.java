package case1.nl.controller;

import java.io.Serializable;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class LoginController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private String mail, pwd;    

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

//</editor-fold>


    @PostConstruct
    public void init()
    {
        logout();
    }
    
    public String login() {

        try {
            User user = session.getUserRepository().getUser(mail, pwd);
            FMessage.info("User "
                    + mail
                    + " logged in.");
            session.setCurrentUser(user);            
            return "index.html";
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            FMessage.warn("Problem logging in user '"
                    + mail
                    + "'.");
            session = null;
            return null;
        }
    }

    public String logout() {
        try {
            session.setCurrentUser(null);
        } catch (Exception ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login.html";
    }
      

}
