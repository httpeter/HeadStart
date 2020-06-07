package case1.nl.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class ItextController implements Serializable {

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
    @PostConstruct
    public void init() {

    }

    public static byte[] renderToPDF(String html) throws Exception {
        File htmlFile = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            htmlFile = new File(html);
            renderer.setDocument(html);
            renderer.layout();
            renderer.createPDF(baos);

            return baos.toByteArray();
        } finally {
            if (htmlFile != null) {
                htmlFile.delete();
            }
        }
    }

}
