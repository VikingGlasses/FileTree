package org.benjamin.filetree.controller;

public interface SelectionModel<S> {

  S getSelected();

  void SetSelected(S s);

  boolean isEmpty();
  
  void clear();

}
