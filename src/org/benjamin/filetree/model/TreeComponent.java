package org.benjamin.filetree.model;

import org.benjamin.filetree.controller.ComponentEnum;

public interface TreeComponent {

  public int getIdentifier();

  public String getText();

  public ComponentEnum getComponentType();

}
