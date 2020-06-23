/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "FOODMOMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodmoment.findAll", query = "SELECT f FROM Foodmoment f"),
    @NamedQuery(name = "Foodmoment.findById", query = "SELECT f FROM Foodmoment f WHERE f.id = :id"),
    @NamedQuery(name = "Foodmoment.findByUserid", query = "SELECT f FROM Foodmoment f WHERE f.userid = :userid"),
    @NamedQuery(name = "Foodmoment.findByMoment", query = "SELECT f FROM Foodmoment f WHERE f.moment = :moment"),
    @NamedQuery(name = "Foodmoment.findByMomenttype", query = "SELECT f FROM Foodmoment f WHERE f.momenttype = :momenttype"),
    @NamedQuery(name = "Foodmoment.findByProduct", query = "SELECT f FROM Foodmoment f WHERE f.product = :product"),
    @NamedQuery(name = "Foodmoment.findByKcal", query = "SELECT f FROM Foodmoment f WHERE f.kcal = :kcal")})
public class Foodmoment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Column(name = "MOMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date moment;
    @Size(max = 45)
    @Column(name = "MOMENTTYPE")
    private String momenttype;
    @Size(max = 255)
    @Column(name = "PRODUCT")
    private String product;
    @Column(name = "KCAL")
    private Integer kcal;

    public Foodmoment() {
    }

    public Foodmoment(Integer id) {
        this.id = id;
    }

    public Foodmoment(Integer id, int userid) {
        this.id = id;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public String getMomenttype() {
        return momenttype;
    }

    public void setMomenttype(String momenttype) {
        this.momenttype = momenttype;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
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
        if (!(object instanceof Foodmoment)) {
            return false;
        }
        Foodmoment other = (Foodmoment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case1.nl.entities.Foodmoment[ id=" + id + " ]";
    }
    
}
