package org.benjamin.filetree.model;

public class History<T> {

  private T id;
  private String text;
  private boolean isSearch;

  public History(T id) {
    this.id = id;
    isSearch = false;
  }
  
  public History(T id, String text) {
    this.id = id;
    this.text = text;
    isSearch = true;
  }

  public T getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean isSearch() {
    return isSearch;
  }

}
