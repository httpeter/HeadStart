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
@Table(name = "shippers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shippers.findAll", query = "SELECT s FROM Shippers s")
    , @NamedQuery(name = "Shippers.findByShipperid", query = "SELECT s FROM Shippers s WHERE s.shipperid = :shipperid")
    , @NamedQuery(name = "Shippers.findByCompanyname", query = "SELECT s FROM Shippers s WHERE s.companyname = :companyname")
    , @NamedQuery(name = "Shippers.findByPhone", query = "SELECT s FROM Shippers s WHERE s.phone = :phone")})
public class Shippers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "shipperid")
    private Integer shipperid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "companyname")
    private String companyname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 24)
    @Column(name = "phone")
    private String phone;

    public Shippers() {
    }

    public Shippers(Integer shipperid) {
        this.shipperid = shipperid;
    }

    public Shippers(Integer shipperid, String companyname) {
        this.shipperid = shipperid;
        this.companyname = companyname;
    }

    public Integer getShipperid() {
        return shipperid;
    }

    public void setShipperid(Integer shipperid) {
        this.shipperid = shipperid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipperid != null ? shipperid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shippers)) {
            return false;
        }
        Shippers other = (Shippers) object;
        if ((this.shipperid == null && other.shipperid != null) || (this.shipperid != null && !this.shipperid.equals(other.shipperid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Shippers[ shipperid=" + shipperid + " ]";
    }
    
}
