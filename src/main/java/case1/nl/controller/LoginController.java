package case1.nl.controller;

import case1.nl.entities.SysPage;
import java.io.Serializable;
import case1.nl.entities.User;
import case1.nl.util.DBVersionController;
import case1.nl.util.FMessage;
import case1.nl.util.Validator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PeterH
 */
@ManagedBean
@ViewScoped
public class LoginController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private HttpServletRequest request;

    private String mail, pwd, t, dbStatus = "";



//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
    public void init() {
        User tempUser = new User();
        tempUser.setLanguage("EN");
        session.setCurrentUser(tempUser);

        request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        t = request.getParameter("t");

        if (t != null) {
            try {

                User user = (User) session.getUserRepository()
                        .findByNamedQueryName("User.findByEmail",
                                session.getCryptor().decrypt(t))
                        .get(0);

                if (user != null) {

                    session.setCurrentUser(user);

                    SysPage redirectPage = (SysPage) session.getSystemRepository()
                            .findByNamedQueryName("SysPage.findById",
                                    session.getCurrentUser()
                                            .getLandingpageid()).get(0);

                    session.getFacesContext()
                            .getExternalContext()
                            .redirect(redirectPage.getValue());
                } else {
                    FMessage.warn("Token not valid");
                }
            } catch (IOException
                    | IllegalBlockSizeException
                    | BadPaddingException ex) {
                Logger.getLogger(LoginController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }



    public String login() {

        User user = null;

        try {
            user = session.getUserRepository()
                    .getUser(mail, session.getCryptor()
                            .encrypt(pwd));
        } catch (UnsupportedEncodingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(LoginController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        if (user != null
                && Validator.isEmailAddress(mail)) {

            FMessage.info("User "
                    + mail
                    + " logged in.");

            session.setCurrentUser(user);

            SysPage sysPage = (SysPage) session.getSystemRepository()
                    .findByNamedQueryName("SysPage.findById", session.getCurrentUser()
                            .getLandingpageid()).get(0);

            return sysPage.getValue() + "?faces-redirect=true";

        } else {
            FMessage.warn("Problem logging in user '"
                    + mail
                    + "'.");
            return "login.html";
        }
    }



    public String logout() {
        try {
            session.setCurrentUser(null);

        } catch (Exception ex) {
            Logger.getLogger(SessionController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return "login.html";
    }



    public String getDBStatus() {

        try {
            DBVersionController dbvc = new DBVersionController();
            int migrations = dbvc.migrateToLatest();
            if (migrations > 0) {
                this.dbStatus = "# of database migrations performed: "
                        + migrations;
                return dbStatus;
            }
        } catch (Exception e) {
            FMessage.fatal(e.getMessage());
            this.dbStatus = e.getMessage();
        }
        return this.dbStatus;
    }

}
