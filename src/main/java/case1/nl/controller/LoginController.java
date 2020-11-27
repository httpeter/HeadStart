package case1.nl.controller;

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
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

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
    
    private String mail, pwd, p, t, dbStatus = "";

    private DashboardModel dashboardModel;

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
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
    @PostConstruct
    public void init() {
        session.setCurrentUser(null);

        request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        //Needs to be done at bean creation time since we are using viewscoped scope
        p = request.getParameter("p");

        t = request.getParameter("t");

        if (t != null) {
            try {

                User user = session.getUserRepository()
                        .getUser(session.getCryptor().decrypt(t));

                if (user != null) {
                    session.setCurrentUser(user);
                    session.getFacesContext()
                            .getExternalContext()
                            .redirect(p + ".html");
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
        loadDasboards();
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

            if (p != null) {
                return p + ".html";
            } else {
                return session.getCurrentUser().getLandingpage()
                        + ".html"
                        + "?faces-redirect=true";
            }

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

    /**
     * Helper function for setting the proper dashboard columns
     */
    private void loadDasboards() {
        dashboardModel = new DefaultDashboardModel();

        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("welcomePanel");
        column2.addWidget("loginPanel");

        dashboardModel.addColumn(column1);
        dashboardModel.addColumn(column2);
    }
    
    

}
