package case1.nl.controller;

import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            FMessage.error(e.getMessage());
        }
        return p;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public SessionController()
    {
        FMessage.info("new SessionContoller instance called");
        
    }

   

}
