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
@Table(name = "NEVO201960")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nevo201960.findAll", query = "SELECT n FROM Nevo201960 n"),
    @NamedQuery(name = "Nevo201960.findById", query = "SELECT n FROM Nevo201960 n WHERE n.id = :id"),
    @NamedQuery(name = "Nevo201960.findByGroupcode", query = "SELECT n FROM Nevo201960 n WHERE n.groupcode = :groupcode"),
    @NamedQuery(name = "Nevo201960.findByGroupdescriptionnl", query = "SELECT n FROM Nevo201960 n WHERE n.groupdescriptionnl = :groupdescriptionnl"),
    @NamedQuery(name = "Nevo201960.findByProdcode", query = "SELECT n FROM Nevo201960 n WHERE n.prodcode = :prodcode"),
    @NamedQuery(name = "Nevo201960.findByProddescnl", query = "SELECT n FROM Nevo201960 n WHERE n.proddescnl = :proddescnl"),
    @NamedQuery(name = "Nevo201960.findByProddescen", query = "SELECT n FROM Nevo201960 n WHERE n.proddescen like :proddescen"),
    @NamedQuery(name = "Nevo201960.findByProdsysnonymsnl", query = "SELECT n FROM Nevo201960 n WHERE n.prodsysnonymsnl = :prodsysnonymsnl"),
    @NamedQuery(name = "Nevo201960.findByUom", query = "SELECT n FROM Nevo201960 n WHERE n.uom = :uom"),
    @NamedQuery(name = "Nevo201960.findByAmount", query = "SELECT n FROM Nevo201960 n WHERE n.amount = :amount"),
    @NamedQuery(name = "Nevo201960.findByKj", query = "SELECT n FROM Nevo201960 n WHERE n.kj = :kj"),
    @NamedQuery(name = "Nevo201960.findByKcal", query = "SELECT n FROM Nevo201960 n WHERE n.kcal = :kcal")})
public class Nevo201960 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "GROUPCODE")
    private Integer groupcode;
    @Size(max = 45)
    @Column(name = "GROUPDESCRIPTIONNL")
    private String groupdescriptionnl;
    @Column(name = "PRODCODE")
    private Integer prodcode;
    @Size(max = 255)
    @Column(name = "PRODDESCNL")
    private String proddescnl;
    @Size(max = 255)
    @Column(name = "PRODDESCEN")
    private String proddescen;
    @Size(max = 255)
    @Column(name = "PRODSYSNONYMSNL")
    private String prodsysnonymsnl;
    @Size(max = 45)
    @Column(name = "UOM")
    private String uom;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "KJ")
    private Integer kj;
    @Column(name = "KCAL")
    private Integer kcal;



    public Nevo201960() {
    }



    public Nevo201960(Integer id) {
        this.id = id;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getGroupcode() {
        return groupcode;
    }



    public void setGroupcode(Integer groupcode) {
        this.groupcode = groupcode;
    }



    public String getGroupdescriptionnl() {
        return groupdescriptionnl;
    }



    public void setGroupdescriptionnl(String groupdescriptionnl) {
        this.groupdescriptionnl = groupdescriptionnl;
    }



    public Integer getProdcode() {
        return prodcode;
    }



    public void setProdcode(Integer prodcode) {
        this.prodcode = prodcode;
    }



    public String getProddescnl() {
        return proddescnl;
    }



    public void setProddescnl(String proddescnl) {
        this.proddescnl = proddescnl;
    }



    public String getProddescen() {
        return proddescen;
    }



    public void setProddescen(String proddescen) {
        this.proddescen = proddescen;
    }



    public String getProdsysnonymsnl() {
        return prodsysnonymsnl;
    }



    public void setProdsysnonymsnl(String prodsysnonymsnl) {
        this.prodsysnonymsnl = prodsysnonymsnl;
    }



    public String getUom() {
        return uom;
    }



    public void setUom(String uom) {
        this.uom = uom;
    }



    public Integer getAmount() {
        return amount;
    }



    public void setAmount(Integer amount) {
        this.amount = amount;
    }



    public Integer getKj() {
        return kj;
    }



    public void setKj(Integer kj) {
        this.kj = kj;
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
        if (!(object instanceof Nevo201960)) {
            return false;
        }
        Nevo201960 other = (Nevo201960) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.Nevo201960[ id=" + id + " ]";
    }
    
}
