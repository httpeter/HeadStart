package case1.nl.controller.compositon;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import case1.nl.controller.SessionController;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class LoginController implements Serializable {
    
    @ManagedProperty(value = "#{sessionController}")    
    private SessionController session;
    
    private User user = new User();
    
  
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public SessionController getSession() {
        return session;
    }
    
    public void setSession(SessionController session) {
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

//</editor-fold>
    
    
   
           
    
    
    public void login()
    {
              FMessage.info("!");
//        if(session.getCurrentUser() == user)
//        {
//            FMessage.info("user known!");
//        }
//        else
//        {
//               FMessage.info("!");
//        }
    }
    
}
