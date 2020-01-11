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
public class ImpactMapController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private TreeNode goal;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public TreeNode getGoal() {
        return goal;
    }

    public void setGoal(TreeNode goal) {
        this.goal = goal;
    }

    //</editor-fold>
    public ImpactMapController() {
    }

    @PostConstruct
    public void init() {
        goal = new DefaultTreeNode("Goal:", null);

        TreeNode actor = new DefaultTreeNode("Actor:", goal);
        TreeNode actor2 = new DefaultTreeNode("Actor:", goal);
        TreeNode actor3 = new DefaultTreeNode("Actor:", goal);

        TreeNode imapct = new DefaultTreeNode("Impact:", actor);

        TreeNode deliverable = new DefaultTreeNode("Deliverable(epic):", imapct);

        TreeNode userStory = new DefaultTreeNode("UserStory:", deliverable);

    }

}
