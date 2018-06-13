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
@Table(name = "territories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Territories.findAll", query = "SELECT t FROM Territories t")
    , @NamedQuery(name = "Territories.findByTerritoryid", query = "SELECT t FROM Territories t WHERE t.territoryid = :territoryid")
    , @NamedQuery(name = "Territories.findByTerritorydescription", query = "SELECT t FROM Territories t WHERE t.territorydescription = :territorydescription")
    , @NamedQuery(name = "Territories.findByRegionid", query = "SELECT t FROM Territories t WHERE t.regionid = :regionid")})
public class Territories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "territoryid")
    private String territoryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "territorydescription")
    private String territorydescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regionid")
    private int regionid;

    public Territories() {
    }

    public Territories(String territoryid) {
        this.territoryid = territoryid;
    }

    public Territories(String territoryid, String territorydescription, int regionid) {
        this.territoryid = territoryid;
        this.territorydescription = territorydescription;
        this.regionid = regionid;
    }

    public String getTerritoryid() {
        return territoryid;
    }

    public void setTerritoryid(String territoryid) {
        this.territoryid = territoryid;
    }

    public String getTerritorydescription() {
        return territorydescription;
    }

    public void setTerritorydescription(String territorydescription) {
        this.territorydescription = territorydescription;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (territoryid != null ? territoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Territories)) {
            return false;
        }
        Territories other = (Territories) object;
        if ((this.territoryid == null && other.territoryid != null) || (this.territoryid != null && !this.territoryid.equals(other.territoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Territories[ territoryid=" + territoryid + " ]";
    }
    
}
