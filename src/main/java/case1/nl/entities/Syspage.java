/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "SYSPAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Syspage.findAll", query = "SELECT s FROM Syspage s"),
    @NamedQuery(name = "Syspage.findById", query = "SELECT s FROM Syspage s WHERE s.id = :id"),
    @NamedQuery(name = "Syspage.findByLabel", query = "SELECT s FROM Syspage s WHERE s.label = :label"),
    @NamedQuery(name = "Syspage.findByValue", query = "SELECT s FROM Syspage s WHERE s.value = :value"),
    @NamedQuery(name = "Syspage.findByIcon", query = "SELECT s FROM Syspage s WHERE s.icon = :icon")})
public class Syspage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45)
    @Column(name = "LABEL")
    private String label;
    @Size(max = 45)
    @Column(name = "VALUE")
    private String value;
    @Size(max = 45)
    @Column(name = "ICON")
    private String icon;



    public Syspage() {
    }



    public Syspage(Integer id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public String getLabel() {
        return label;
    }



    public void setLabel(String label) {
        this.label = label;
    }



    public String getValue() {
        return value;
    }



    public void setValue(String value) {
        this.value = value;
    }



    public String getIcon() {
        return icon;
    }



    public void setIcon(String icon) {
        this.icon = icon;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Syspage)) {
            return false;
        }
        Syspage other = (Syspage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.Syspage[ id=" + id + " ]";
    }
    
}
