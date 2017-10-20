package org.benjamin.filetree.controller;

public class MySelectionModel<S> implements SelectionModel<S> {
  
  private S selected;

  @Override
  public S getSelected() {
    return selected;
  }

  @Override
  public void SetSelected(S s) {
    selected = s;
  }

  @Override
  public boolean isEmpty() {
    return selected == null;
  }

  @Override
  public void clear() {
    selected = null;
  }

}
