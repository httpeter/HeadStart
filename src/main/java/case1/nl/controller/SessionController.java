/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.controller;

;

import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import case1.nl.data.repository.DefaultRepository;
import case1.nl.data.repository.UserRepository;
import case1.nl.entities.User;

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

    public DefaultRepository getDB() {
        return DefaultRepository.getInstance("PU");
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

    public String getCompositionsDir() {
        return getFacesContext().getExternalContext()
                .getInitParameter("compositionsDir");
    }

    public User getCurrentUser() {
        return UserRepository.getInstance("PU").getUser("httpeter@gmail.com");
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
