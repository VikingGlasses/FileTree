package org.benjamin.filetree.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.benjamin.filetree.controller.ComponentTypeEnum;
import org.benjamin.filetree.model.TreeComponent;

/**
 * POJO for representing a DB entity leaf that implements TreeComponent.
 * @see TreeComponent
 * @author benjamin
 *
 */
@Entity
@Table(name="leaf")
public class Leaf implements TreeComponent {
  
  @Id
  @Column(name="id")
  private int id;
  
  @ManyToOne
  @JoinColumn(name="parent_id")
  private Branch parent;
  
  @ManyToOne
  @JoinColumn(name="type")
  private Type type;
  
  @Column(name="name")
  private String name;
  
  @Column(name="color")
  private String color;
  
  public Leaf() {}

  public Leaf(String name, String color, Branch parent) {
    super();
    this.name = name;
    this.color = color;
    this.parent = parent;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Branch getParent() {
    return parent;
  }

  public void setParent(Branch parent) {
    this.parent = parent;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Leaf [id=%s, name=%s, color=%s, parent=%d]", id, name, color, parent.getIdentifier());
  }

  @Override
  public int getIdentifier() {
    return id;
  }

  @Override
  public String getText() {
    return name;
  }

  @Override
  public ComponentTypeEnum getComponentType() {
    return ComponentTypeEnum.LEAF;
  }

}
