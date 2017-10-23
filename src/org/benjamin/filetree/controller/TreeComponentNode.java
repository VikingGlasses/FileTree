package org.benjamin.filetree.controller;

import javafx.scene.control.Label;

public class TreeComponentNode extends Label {

  private int identifier;
  private ComponentEnum type;
  
  public TreeComponentNode() {
    super();
  }

  /**
   * @param identifier
   * @param type
   */
  public TreeComponentNode(int identifier, ComponentEnum type) {
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
  public ComponentEnum getType() {
    return type;
  }
  public void setType(ComponentEnum type) {
    this.type = type;
  }

}
