package org.benjamin.filetree.model;

import org.benjamin.filetree.controller.ComponentTypeEnum;

/**
 * Interface for representing a tree component
 * @author benjamin
 *
 */
public interface TreeComponent {

  /**
   * Returns this components id.
   * @return
   */
  public int getIdentifier();

  /**
   * Returns this components text.
   * @return
   */
  public String getText();

  /**
   * Returns this components type.
   * @return
   */
  public ComponentTypeEnum getComponentType();

}
