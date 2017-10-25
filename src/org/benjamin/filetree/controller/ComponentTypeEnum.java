package org.benjamin.filetree.controller;

import org.benjamin.filetree.model.TreeComponent;

/**
 * Defines tree component types.
 * @see TreeComponent
 * @author benjamin
 *
 */
public enum ComponentTypeEnum {
  
  COMPOSITE,
  LEAF;
  
  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
