package org.benjamin.filetree.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.benjamin.filetree.controller.ComponentEnum;
import org.benjamin.filetree.controller.TreeComponentNodeFactory;
import org.benjamin.filetree.model.TreeComponent;

@Entity
@Table(name="branch")
public class Branch implements TreeComponent {
  
  @Id
  @Column(name="id")
  private int id;
  
  @ManyToOne
  @JoinColumn(name="parent_id")
  private Branch parent;
  
  @Column(name="name")
  private String name;
  
  @OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
  private Set<Branch> children = new HashSet<>();
  
  @OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
  private Set<Leaf> leafs = new HashSet<>();
  
  public Branch() {}

  public Branch(Branch parent, String name) {
    super();
    this.parent = parent;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Branch getParent() {
    return parent;
  }

  public void setParent(Branch parent) {
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Branch> getChildren() {
    return children;
  }

  public void setChildren(Set<Branch> children) {
    this.children = children;
  }

  public Set<Leaf> getLeafs() {
    return leafs;
  }

  public void setLeafs(Set<Leaf> leafs) {
    this.leafs = leafs;
  }

  @Override
  public String toString() {
    return String.format("Branch [id=%s, parent=%s]", id, parent);
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
  public ComponentEnum getComponentType() {
    return ComponentEnum.BRANCH;
  }

}
