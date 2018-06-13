/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.httpeter.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderid", query = "SELECT o FROM Orders o WHERE o.orderid = :orderid")
    , @NamedQuery(name = "Orders.findByCustomerid", query = "SELECT o FROM Orders o WHERE o.customerid = :customerid")
    , @NamedQuery(name = "Orders.findByEmployeeid", query = "SELECT o FROM Orders o WHERE o.employeeid = :employeeid")
    , @NamedQuery(name = "Orders.findByOrderdate", query = "SELECT o FROM Orders o WHERE o.orderdate = :orderdate")
    , @NamedQuery(name = "Orders.findByRequireddate", query = "SELECT o FROM Orders o WHERE o.requireddate = :requireddate")
    , @NamedQuery(name = "Orders.findByShippeddate", query = "SELECT o FROM Orders o WHERE o.shippeddate = :shippeddate")
    , @NamedQuery(name = "Orders.findByShipvia", query = "SELECT o FROM Orders o WHERE o.shipvia = :shipvia")
    , @NamedQuery(name = "Orders.findByFreight", query = "SELECT o FROM Orders o WHERE o.freight = :freight")
    , @NamedQuery(name = "Orders.findByShipname", query = "SELECT o FROM Orders o WHERE o.shipname = :shipname")
    , @NamedQuery(name = "Orders.findByShipaddress", query = "SELECT o FROM Orders o WHERE o.shipaddress = :shipaddress")
    , @NamedQuery(name = "Orders.findByShipcity", query = "SELECT o FROM Orders o WHERE o.shipcity = :shipcity")
    , @NamedQuery(name = "Orders.findByShipregion", query = "SELECT o FROM Orders o WHERE o.shipregion = :shipregion")
    , @NamedQuery(name = "Orders.findByShippostalcode", query = "SELECT o FROM Orders o WHERE o.shippostalcode = :shippostalcode")
    , @NamedQuery(name = "Orders.findByShipcountry", query = "SELECT o FROM Orders o WHERE o.shipcountry = :shipcountry")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderid")
    private Integer orderid;
    @Size(max = 5)
    @Column(name = "customerid")
    private String customerid;
    @Column(name = "employeeid")
    private Integer employeeid;
    @Column(name = "orderdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @Column(name = "requireddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requireddate;
    @Column(name = "shippeddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shippeddate;
    @Column(name = "shipvia")
    private Integer shipvia;
    @Column(name = "freight")
    private Integer freight;
    @Size(max = 40)
    @Column(name = "shipname")
    private String shipname;
    @Size(max = 60)
    @Column(name = "shipaddress")
    private String shipaddress;
    @Size(max = 15)
    @Column(name = "shipcity")
    private String shipcity;
    @Size(max = 15)
    @Column(name = "shipregion")
    private String shipregion;
    @Size(max = 10)
    @Column(name = "shippostalcode")
    private String shippostalcode;
    @Size(max = 15)
    @Column(name = "shipcountry")
    private String shipcountry;

    public Orders() {
    }

    public Orders(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(Date requireddate) {
        this.requireddate = requireddate;
    }

    public Date getShippeddate() {
        return shippeddate;
    }

    public void setShippeddate(Date shippeddate) {
        this.shippeddate = shippeddate;
    }

    public Integer getShipvia() {
        return shipvia;
    }

    public void setShipvia(Integer shipvia) {
        this.shipvia = shipvia;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getShipname() {
        return shipname;
    }

    public void setShipname(String shipname) {
        this.shipname = shipname;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress;
    }

    public String getShipcity() {
        return shipcity;
    }

    public void setShipcity(String shipcity) {
        this.shipcity = shipcity;
    }

    public String getShipregion() {
        return shipregion;
    }

    public void setShipregion(String shipregion) {
        this.shipregion = shipregion;
    }

    public String getShippostalcode() {
        return shippostalcode;
    }

    public void setShippostalcode(String shippostalcode) {
        this.shippostalcode = shippostalcode;
    }

    public String getShipcountry() {
        return shipcountry;
    }

    public void setShipcountry(String shipcountry) {
        this.shipcountry = shipcountry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Orders[ orderid=" + orderid + " ]";
    }
    
}
