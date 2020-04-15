package case1.nl.controller;

import case1.nl.entities.Foodmoment;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class PeetPatroonController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List<Foodmoment> foodMoments;

    private Foodmoment newFoodMoment;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public List<Foodmoment> getFoodMoments() {
        return foodMoments;
    }

    public void setFoodMoments(List<Foodmoment> foodMoments) {
        this.foodMoments = foodMoments;
    }

    public Foodmoment getNewFoodMoment() {
        return newFoodMoment;
    }

    public void setNewFoodMoment(Foodmoment newFoodMoment) {
        this.newFoodMoment = newFoodMoment;
    }
    
    
    

    //</editor-fold>
    public PeetPatroonController() {
    }

    @PostConstruct
    public void init() {

        newFoodMoment = new Foodmoment();              
        this.loadMoments();
    }
    
    public void loadMoments()
    {
         foodMoments = session.getFoodMomentRepository()
                .getResultList(Foodmoment.class);
    }
    
    public void save()
    {
        if(newFoodMoment.getMoment() != null 
                && session.getFoodMomentRepository().persisted(newFoodMoment))
        {
            FMessage.info("Moment saved");
            newFoodMoment = new Foodmoment();
            this.loadMoments();
        }
        else
        {
            FMessage.warn("Moment empty");
        }
    }

}
