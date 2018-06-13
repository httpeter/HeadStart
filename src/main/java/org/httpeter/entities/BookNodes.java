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
@Table(name = "book_nodes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookNodes.findAll", query = "SELECT b FROM BookNodes b")
    , @NamedQuery(name = "BookNodes.findByParentId", query = "SELECT b FROM BookNodes b WHERE b.parentId = :parentId")
    , @NamedQuery(name = "BookNodes.findByLabelText", query = "SELECT b FROM BookNodes b WHERE b.labelText = :labelText")
    , @NamedQuery(name = "BookNodes.findByNodeId", query = "SELECT b FROM BookNodes b WHERE b.nodeId = :nodeId")})
public class BookNodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "parent_id")
    private Integer parentId;
    @Size(max = 255)
    @Column(name = "label_text")
    private String labelText;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "node_id")
    private Integer nodeId;

    public BookNodes() {
    }

    public BookNodes(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookNodes)) {
            return false;
        }
        BookNodes other = (BookNodes) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.BookNodes[ nodeId=" + nodeId + " ]";
    }
    
}
