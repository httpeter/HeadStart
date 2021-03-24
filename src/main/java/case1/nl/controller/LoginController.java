package case1.nl.controller;

import case1.nl.entities.Syspage;
import java.io.Serializable;
import case1.nl.entities.User;
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
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

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

    private TagCloudModel tagCloudModel;

    private String mail, pwd, t, dbStatus = "";



//<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public TagCloudModel getTagCloudModel() {
        return tagCloudModel;
    }



    public void setTagCloudModel(TagCloudModel tagCloudModel) {
        this.tagCloudModel = tagCloudModel;
    }



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



    public LoginController() {
        this.fillTagCloud();
    }



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

                    Syspage redirectPage = (Syspage) session.getSystemRepository()
                            .findByNamedQueryName("Syspage.findById",
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



    private void fillTagCloud() {
        tagCloudModel = new DefaultTagCloudModel();        
        tagCloudModel.addTag(new DefaultTagCloudItem("design", 1));
        tagCloudModel.addTag(new DefaultTagCloudItem("users", "http://www.nu.nl", 3));
        tagCloudModel.addTag(new DefaultTagCloudItem("application", 2));
        tagCloudModel.addTag(new DefaultTagCloudItem("quality", "#", 5));
        tagCloudModel.addTag(new DefaultTagCloudItem("interface", 4));
        tagCloudModel.addTag(new DefaultTagCloudItem("team", "#", 2));
        tagCloudModel.addTag(new DefaultTagCloudItem("product", 5));
        tagCloudModel.addTag(new DefaultTagCloudItem("data", 3));
        tagCloudModel.addTag(new DefaultTagCloudItem("usability", "#", 4));
        tagCloudModel.addTag(new DefaultTagCloudItem("experience", "#", 1));        
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

            Syspage sysPage = (Syspage) session.getSystemRepository()
                    .findByNamedQueryName("Syspage.findById", session.getCurrentUser()
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

//Turned off for performance reasons...
//        try {
//            DBVersionController dbvc = new DBVersionController();
//            int migrations = dbvc.migrateToLatest();
//            if (migrations > 0) {
//                this.dbStatus = "# of database migrations performed: "
//                        + migrations;
//                return dbStatus;
//            }
//        } catch (Exception e) {
//            FMessage.fatal(e.getMessage());
//            this.dbStatus = e.getMessage();
//        }
        return this.dbStatus;
    }

}
