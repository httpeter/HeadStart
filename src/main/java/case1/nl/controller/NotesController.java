package case1.nl.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class NotesController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

     //</editor-fold>
    public NotesController() {
    }

    @PostConstruct
    public void init() {

    }

}
