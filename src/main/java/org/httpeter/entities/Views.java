/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.httpeter.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "views")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Views.findAll", query = "SELECT v FROM Views v")
    , @NamedQuery(name = "Views.findByName", query = "SELECT v FROM Views v WHERE v.name = :name")
    , @NamedQuery(name = "Views.findByColumnstate", query = "SELECT v FROM Views v WHERE v.columnstate = :columnstate")
    , @NamedQuery(name = "Views.findByFormName", query = "SELECT v FROM Views v WHERE v.formName = :formName")})
public class Views implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2000)
    @Column(name = "columnstate")
    private String columnstate;
    @Size(max = 50)
    @Column(name = "form_name")
    private String formName;

    public Views() {
    }

    public Views(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnstate() {
        return columnstate;
    }

    public void setColumnstate(String columnstate) {
        this.columnstate = columnstate;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Views)) {
            return false;
        }
        Views other = (Views) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Views[ name=" + name + " ]";
    }
    
}
