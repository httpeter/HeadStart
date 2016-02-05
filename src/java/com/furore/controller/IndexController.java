package com.furore.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable
{

    private final FacesContext facesContext;

    private final String compositionsDir;

    private final boolean developmentStage;

    private String labelFile,
            currentComposition;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public boolean isDevelopmentStage()
    {
        return developmentStage;
    }



    public String getCompositionsDir()
    {
        return compositionsDir;
    }



    public String getCurrentComposition()
    {
        return currentComposition;
    }



    public void setCurrentComposition(String currentComposition)
    {
        this.currentComposition = currentComposition;
    }



    public String getLabelFile()
    {
        return labelFile;
    }



    public void setLabelFile(String labelFile)
    {
        this.labelFile = labelFile;
    }

//</editor-fold>


    public IndexController()
    {
        facesContext = FacesContext.getCurrentInstance();

        developmentStage = facesContext.getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development");

        labelFile = facesContext.getExternalContext()
                .getInitParameter("labelFile");

        compositionsDir = facesContext.getExternalContext()
                .getInitParameter("compositionsDir");

        this.navigate();
    }



    private void navigate()
    {
        String p = facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("p");

        if (p == null)
        {
            p = "home";
        }

        this.currentComposition = compositionsDir
                + p
                + ".xhtml";
    }

}
