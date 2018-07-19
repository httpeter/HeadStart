/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.httpeter.controller;

;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author peterhendriks
 */


@ManagedBean
@SessionScoped
public class SessionController implements Serializable {

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
                .getInitParameter("labelFile");
    }

    public String getCompositionsDir() {
        return getFacesContext().getExternalContext()
                .getInitParameter("compositionsDir");
    }

}
