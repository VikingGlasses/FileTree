package org.benjamin.filetree.model;

import org.benjamin.filetree.controller.ComponentTypeEnum;

import javafx.scene.control.Label;

/**
 * A view representation of a  tree component.
 * @see TreeComponent
 * @author benjamin
 *
 */
public class TreeComponentNode extends Label {

  private int identifier;
  private ComponentTypeEnum type;
  
  public TreeComponentNode() {
    super();
  }

  /**
   * @param identifier
   * @param type
   */
  public TreeComponentNode(int identifier, ComponentTypeEnum type) {
    super();
    this.identifier = identifier;
    this.type = type;
  }

  public int getIdentifier() {
    return identifier;
  }
  public void setIdentifier(int identifier) {
    this.identifier = identifier;
  }
  public ComponentTypeEnum getType() {
    return type;
  }
  public void setType(ComponentTypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TreeComponentNode other = (TreeComponentNode) obj;
    if (identifier != other.identifier)
      return false;
    if (type != other.type)
      return false;
    return true;
  }

}
