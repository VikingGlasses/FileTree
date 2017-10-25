package org.benjamin.filetree.controller;

/**
 * Defines TreeComponent types.
 * @author benjamin
 *
 */
public enum ComponentEnum {
  
  COMPOSITE,
  LEAF;
  
  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
