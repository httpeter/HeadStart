package case1.nl.controller;

import case1.nl.entities.Foodmoment;
import case1.nl.entities.Nevo201960;
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
public class FoodMomentController implements Serializable {

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
        this.foodMoments = session.getFoodMomentRepository()
                .findByNamedQueryName("Foodmoment.findByUserid",
                        session.getCurrentUser().getId());

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

            Nevo201960 nevo201960 = (Nevo201960) session.getFoodMomentRepository()
                    .findByNamedQueryName("Nevo201960.findByProddescen", newFoodMoment
                            .getProduct())
                    .get(0);

            newFoodMoment.setKcal(nevo201960.getKcal());

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
