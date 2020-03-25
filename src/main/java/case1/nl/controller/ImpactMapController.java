package case1.nl.controller;

import case1.nl.util.FMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<TreeNode> impactMaps;
    
    private TreeNode selectedIM;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List<TreeNode> getImpactMaps() {
        return impactMaps;
    }
    
    public void setImpactMaps(List<TreeNode> impactMaps) {
        this.impactMaps = impactMaps;
    }
    
    public TreeNode getSelectedIM() {
        return selectedIM;
    }
    
    public void setSelectedIM(TreeNode selectedIM) {
        this.selectedIM = selectedIM;
    }
    
    public SessionController getSession() {
        return session;
    }
    
    public void setSession(SessionController session) {
        this.session = session;
    }

    //</editor-fold>
    public ImpactMapController() {
    }
    
    @PostConstruct
    public void init() {
        
        impactMaps = new ArrayList();
        
    }
    
    public void newIM() {
        
        TreeNode impactMap = new DefaultTreeNode("Goal:", null);
        
        TreeNode actor = new DefaultTreeNode("Actor:", impactMap);
        
        TreeNode imapct = new DefaultTreeNode("Impact:", actor);
        
        TreeNode deliverable = new DefaultTreeNode("Deliverable(epic):", imapct);
        
        TreeNode userStory = new DefaultTreeNode("UserStory:", deliverable);
        
        impactMaps.add(impactMap);
        
    }
    
    public void showChildren() {
        if (!impactMaps.isEmpty()) {
            impactMaps.forEach(im -> {
                FMessage.info("Goal: '"im.getData().toString()+"' children: " +im.getChildren().toString());
                
            });
            
        } else {
            FMessage.error("No maps found");
        }
        
    }
    
}
