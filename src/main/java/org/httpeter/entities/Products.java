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
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findByProductid", query = "SELECT p FROM Products p WHERE p.productid = :productid")
    , @NamedQuery(name = "Products.findByProductname", query = "SELECT p FROM Products p WHERE p.productname = :productname")
    , @NamedQuery(name = "Products.findBySupplierid", query = "SELECT p FROM Products p WHERE p.supplierid = :supplierid")
    , @NamedQuery(name = "Products.findByCategoryid", query = "SELECT p FROM Products p WHERE p.categoryid = :categoryid")
    , @NamedQuery(name = "Products.findByQuantityperunit", query = "SELECT p FROM Products p WHERE p.quantityperunit = :quantityperunit")
    , @NamedQuery(name = "Products.findByUnitprice", query = "SELECT p FROM Products p WHERE p.unitprice = :unitprice")
    , @NamedQuery(name = "Products.findByUnitsinstock", query = "SELECT p FROM Products p WHERE p.unitsinstock = :unitsinstock")
    , @NamedQuery(name = "Products.findByUnitsonorder", query = "SELECT p FROM Products p WHERE p.unitsonorder = :unitsonorder")
    , @NamedQuery(name = "Products.findByReorderlevel", query = "SELECT p FROM Products p WHERE p.reorderlevel = :reorderlevel")
    , @NamedQuery(name = "Products.findByDiscontinued", query = "SELECT p FROM Products p WHERE p.discontinued = :discontinued")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "productid")
    private Integer productid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "productname")
    private String productname;
    @Column(name = "supplierid")
    private Integer supplierid;
    @Column(name = "categoryid")
    private Integer categoryid;
    @Size(max = 20)
    @Column(name = "quantityperunit")
    private String quantityperunit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitprice")
    private Double unitprice;
    @Column(name = "unitsinstock")
    private Integer unitsinstock;
    @Column(name = "unitsonorder")
    private Integer unitsonorder;
    @Column(name = "reorderlevel")
    private Integer reorderlevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discontinued")
    private int discontinued;

    public Products() {
    }

    public Products(Integer productid) {
        this.productid = productid;
    }

    public Products(Integer productid, String productname, int discontinued) {
        this.productid = productid;
        this.productname = productname;
        this.discontinued = discontinued;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(Integer unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public Integer getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(Integer unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public Integer getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(Integer reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Products[ productid=" + productid + " ]";
    }
    
}
