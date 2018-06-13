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
@Table(name = "book_text")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookText.findAll", query = "SELECT b FROM BookText b")
    , @NamedQuery(name = "BookText.findByNoteText", query = "SELECT b FROM BookText b WHERE b.noteText = :noteText")
    , @NamedQuery(name = "BookText.findByNodeId", query = "SELECT b FROM BookText b WHERE b.nodeId = :nodeId")
    , @NamedQuery(name = "BookText.findByCommentText", query = "SELECT b FROM BookText b WHERE b.commentText = :commentText")
    , @NamedQuery(name = "BookText.findByItemText", query = "SELECT b FROM BookText b WHERE b.itemText = :itemText")
    , @NamedQuery(name = "BookText.findByBookTextId", query = "SELECT b FROM BookText b WHERE b.bookTextId = :bookTextId")})
public class BookText implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 8000)
    @Column(name = "note_text")
    private String noteText;
    @Column(name = "node_id")
    private Integer nodeId;
    @Size(max = 8000)
    @Column(name = "comment_text")
    private String commentText;
    @Size(max = 8000)
    @Column(name = "item_text")
    private String itemText;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_text_id")
    private Integer bookTextId;

    public BookText() {
    }

    public BookText(Integer bookTextId) {
        this.bookTextId = bookTextId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Integer getBookTextId() {
        return bookTextId;
    }

    public void setBookTextId(Integer bookTextId) {
        this.bookTextId = bookTextId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookTextId != null ? bookTextId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookText)) {
            return false;
        }
        BookText other = (BookText) object;
        if ((this.bookTextId == null && other.bookTextId != null) || (this.bookTextId != null && !this.bookTextId.equals(other.bookTextId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.BookText[ bookTextId=" + bookTextId + " ]";
    }
    
}
