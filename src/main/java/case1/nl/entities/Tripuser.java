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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "TRIPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tripuser.findAll", query = "SELECT t FROM Tripuser t"),
    @NamedQuery(name = "Tripuser.findById", query = "SELECT t FROM Tripuser t WHERE t.id = :id"),
    @NamedQuery(name = "Tripuser.findByTripid", query = "SELECT t FROM Tripuser t WHERE t.tripid = :tripid"),
    @NamedQuery(name = "Tripuser.findByUserid", query = "SELECT t FROM Tripuser t WHERE t.userid = :userid")})
public class Tripuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TRIPID")
    private Integer tripid;
    @Column(name = "USERID")
    private Integer userid;



    public Tripuser() {
    }



    public Tripuser(Integer id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getTripid() {
        return tripid;
    }



    public void setTripid(Integer tripid) {
        this.tripid = tripid;
    }



    public Integer getUserid() {
        return userid;
    }



    public void setUserid(Integer userid) {
        this.userid = userid;
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
        if (!(object instanceof Tripuser)) {
            return false;
        }
        Tripuser other = (Tripuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.Tripuser[ id=" + id + " ]";
    }
    
}
