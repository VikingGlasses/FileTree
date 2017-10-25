package org.benjamin.filetree.controller;

/**
 * Simple Implementation of a single selection model.
 * @author benjamin
 *
 * @param <T>
 */
public class MySingleSelectionModel<T> {
  
  private T selected;

  /**
   * Clears the selection. Sets the selection to empty.
   */
  public void clearSelection() {
    selected = null;
  }

  /**
   * Sets the selected object.
   * @param t The object that is currently selected.
   */
  public void select(T t) {
    selected = t;
  }

  /**
   * Returns true if nothing is selected.
   * @return
   */
  public boolean isEmpty() {
    return selected == null;
  }

  /**
   * Returns the selected item.
   * @return
   */
  public T getSelectedItem() {
    return selected;
  }

}
