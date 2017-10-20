package org.benjamin.filetree.controller;

import javafx.scene.control.Label;

public class TreeComponentNode extends Label {

  private int identifier;

  public int getIdentifier() {
    return identifier;
  }

  public ComponentEnum getType() {
    return ComponentEnum.COMPOSITE;
  }

}
