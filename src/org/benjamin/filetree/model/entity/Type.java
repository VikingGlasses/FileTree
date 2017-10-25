package org.benjamin.filetree.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.benjamin.filetree.model.TreeComponent;

/**
 * POJO for representing a DB entity.
 * @see TreeComponent
 * @author benjamin
 *
 */
@Entity
@Table(name="type")
public class Type {

  @Id
  @Column(name="id")
  private int id;
  
  @Column(name="name")
  private String name;
  
  public Type() {}
  
  public Type(String name) {
    super();
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Type [id=%s, name=%s]", id, name);
  }
  
}
