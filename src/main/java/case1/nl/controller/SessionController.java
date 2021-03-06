package case1.nl.controller;

import case1.nl.data.repository.DefaultRepository;
import case1.nl.data.repository.FoodMomentRepository;
import case1.nl.data.repository.PlacesRepository;
import case1.nl.data.repository.SystemRepository;
import case1.nl.data.repository.UserRepository;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import case1.nl.entities.User;
import case1.nl.util.AESEncryptor;
import case1.nl.util.FMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author peterhendriks
 */
@ManagedBean(eager = true)
@SessionScoped
public class SessionController implements Serializable {

    private User currentUser;

    private AESEncryptor cryptor;

    private PlacesRepository placesRepository;

    private FoodMomentRepository foodMomentRepository;

    private UserRepository userRepository;

    private SystemRepository systemRepository;

    private DefaultRepository defaultRepository;

    private final String persistenceUnitName = "PU";



    public SessionController() {

        if (cryptor == null) {
            try {
                cryptor = new AESEncryptor();
            } catch (Exception ex) {
                Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }



    @PostConstruct
    public void init() {
        currentUser = new User();
        currentUser.setLanguage("EN");
    }



    public SystemRepository getSystemRepository() {
        if (systemRepository == null) {
            systemRepository = new SystemRepository(persistenceUnitName);
        }
        return systemRepository;
    }



    public FoodMomentRepository getFoodMomentRepository() {
        if (foodMomentRepository == null) {
            foodMomentRepository = new FoodMomentRepository(persistenceUnitName);
        }
        return foodMomentRepository;
    }



    public PlacesRepository getPlacesRepository() {
        if (placesRepository == null) {
            placesRepository = new PlacesRepository(persistenceUnitName);
        }
        return placesRepository;
    }



    public UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository(persistenceUnitName);
        }
        return userRepository;
    }



    public DefaultRepository getDefaultRepository() {
        if (defaultRepository == null) {
            defaultRepository = new DefaultRepository(persistenceUnitName);
        }
        return defaultRepository;
    }



    public FacesContext getFacesContext() {

        return FacesContext.getCurrentInstance();
    }



    public boolean isDevelopmentStage() {
        return getFacesContext().getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development");
    }



    public String getLabelFile() {
        return getFacesContext().getExternalContext()
                .getInitParameter("labelFile") + currentUser.getLanguage();
    }



    public Properties getLabels() {
        Properties p = new Properties();
        try {
            p.load(getClass()
                    .getClassLoader()
                    .getResourceAsStream(this.getLabelFile()
                            + currentUser.getLanguage()
                            + ".properties"));
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            FMessage.error(e.getMessage());
        }
        return p;
    }



    public User getCurrentUser() {
        return currentUser;
    }



    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }



    public String getRedirectToLogin() {

        if (currentUser.getFirstname() == null) {
            FacesContext.getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(), null, "login.htm");
        }

        return null;
    }



    public AESEncryptor getCryptor() {
        return cryptor;
    }



    public void setCryptor(AESEncryptor cryptor) {
        this.cryptor = cryptor;
    }

}
