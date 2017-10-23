package org.benjamin.filetree.controller;

public enum ComponentEnum {
  
  BRANCH,
  LEAF;
  
  @Override
  public String toString() {
    return name().toLowerCase();
  }

}
