package case1.nl.controller;

import case1.nl.entities.Foodmoment;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class FoodMomentController implements Serializable {
    
    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;
    
    private List<Foodmoment> foodMoments;
    
    private Foodmoment newFoodMoment;
    
    private Map kCalsPerDay;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }
    
    public void setSession(SessionController session) {
        this.session = session;
    }
    
    public List<Foodmoment> getFoodMoments() {
        this.foodMoments = session.getFoodMomentRepository()
                .getFoodMoments(session.getCurrentUser()
                        .getId());
        
        kCalsPerDay = new HashMap();
        
        Date datePointer = foodMoments.get(0).getMoment();
        int kCalCounter = 0;
        
        for(int i=0;i>foodMoments.size();i++){                        
            if(datePointer == foodMoments.get(i).getMoment())
            {
                kCalCounter += foodMoments.get(i).getKcal();
                kCalsPerDay.put(datePointer, kCalCounter);
            }
            else
            {   datePointer = foodMoments.get(i).getMoment();
                kCalCounter = 0;
            }
        }
        
        System.out.println("========> " +kCalsPerDay);
        
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
    @PostConstruct
    public void init() {
        newFoodMoment = new Foodmoment();
        newFoodMoment.setUserid(session.getCurrentUser().getId());
    }
    
    public List<String> completeProducts(String query) {
        return session.getFoodMomentRepository()
                .getProdListByDescEN(query);
    }
    
    public void save() {
        
        if (newFoodMoment.getMoment() != null) {
            
            int kCal = session.getFoodMomentRepository()
                    .getProdByDescEN(newFoodMoment.getProduct())
                    .getKcal();
            
            newFoodMoment.setKcal(kCal);
            
            if (session.getFoodMomentRepository()
                    .persisted(newFoodMoment)) {
                FMessage.info(session.getLabels().getProperty("msgMomentSaved"));
            } else {
                FMessage.warn(session.getLabels().getProperty("msgMomentEmpty"));
            }
        }
        //Refreshing the list
        this.getFoodMoments();
        newFoodMoment = new Foodmoment();
        newFoodMoment.setUserid(session.getCurrentUser().getId());
    }
    
}
