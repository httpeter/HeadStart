package case1.nl.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    //<editor-fold defaultstate="collapsed" desc="comment">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }
//</editor-fold>

    public String getCurrentComposition() {

        String p = session.getFacesContext()
                .getExternalContext()
                .getRequestParameterMap()
                .get("p");

        if (p == null || session.getCurrentUser() == null) {
            p = "login";
        }

        return session.getCompositionsDir() + p + ".xhtml";
    }

}
