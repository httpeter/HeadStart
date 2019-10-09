package case1.nl.controller.compositon;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import case1.nl.controller.SessionController;
import case1.nl.data.repository.UserRepository;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private String mail, pwd;

    UserRepository userRepository = new UserRepository("PU");

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

//</editor-fold>
    public void login() {

        try {
            User user = userRepository.getUser(mail, pwd);
            FMessage.info("User "
                    + mail
                    + " logged in.");
            session.setCurrentUser(user);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            FMessage.warn("Problem logging in user '"
                    + mail
                    + "'.");
        }
    }

    public void logout() {
        FMessage.fatal("User '"
                + session.getCurrentUser().getEmail()
                + "' logged out.");
        session.setCurrentUser(null);
    }

    public void test() {
        try {
            Sardine sardine = SardineFactory.begin("httpeter", "Morsstraat4!");
            List<DavResource> resources;

            resources = sardine.list("https://peterveter.stackstorage.com/remote.php/webdav/");

            for (DavResource res : resources) {
                System.out.println(res); // calls the .toString() method.

                InputStream is = sardine.get("peterveter.stackstorage.com/remote.php/webdav/gedeeld/KPN Hotspots FON.txt");
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
