package org.benjamin.filetree.model;

public interface FileTreeModel {

  void back();

  boolean backIsEmpty();

  void forward();

  boolean forwardIsEmpty();

  TreeComponent createNewBranch();

  TreeComponent createNewLeaf();

}
