package case1.nl.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import case1.nl.controller.SessionController;
import case1.nl.data.repository.UserRepository;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;

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

    UserRepository userRepository = new UserRepository("PU");

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
    public String login() {

        try {
            User user = userRepository.getUser(mail, pwd);
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
            return null;
        }
    }

    public String logout() {
        FMessage.fatal("User '"
                + session.getCurrentUser().getEmail()
                + "' logged out.");
        session.setCurrentUser(null);
        return "login.html";
    }

}
