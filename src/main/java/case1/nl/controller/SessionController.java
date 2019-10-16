package case1.nl.controller;

import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import case1.nl.entities.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author peterhendriks
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

    private User currentUser;

    public FacesContext getFacesContext() {

        return FacesContext.getCurrentInstance();
    }

    public boolean isDevelopmentStage() {
        return getFacesContext().getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development");
    }

    public String getLabelFile() {
        return getFacesContext().getExternalContext()
                .getInitParameter("labelFile");
    }

    public Properties getLabels() {
        Properties p = new Properties();
        try {
            p.load(getClass()
                    .getClassLoader()
                    .getResourceAsStream(this.getLabelFile()
                            + ".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public User getCurrentUser() {

        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
