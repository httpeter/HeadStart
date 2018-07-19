package org.httpeter.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    //<editor-fold defaultstate="collapsed" desc="comment">
    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }
//</editor-fold>

    public String getCurrentComposition() {

        String p = sessionController.getFacesContext()
                .getExternalContext()
                .getRequestParameterMap()
                .get("p");

        if (p == null) {
            p = "home";
        }

        return sessionController.getCompositionsDir() + p + ".xhtml";
    }

}
